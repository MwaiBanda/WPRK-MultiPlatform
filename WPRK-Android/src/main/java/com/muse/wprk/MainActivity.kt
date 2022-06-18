package com.muse.wprk

import android.content.Context
import android.media.AudioManager
import android.media.AudioManager.*
import android.media.audiofx.LoudnessEnhancer
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.muse.wprk.core.utilities.ConnectivityStatus
import com.muse.wprk.core.utilities.NavigationRoutes.*
import com.muse.wprk.core.utilities.NotificationWorker
import com.muse.wprk.core.utilities.ShowTime
import com.muse.wprk.main.PodcastHome
import com.muse.wprk.main.model.Show
import com.muse.wprk.presentation.podcasts.PodcastDetail
import com.muse.wprk.presentation.podcasts.PodcastViewModel
import com.muse.wprk.presentation.shows.ShowDetail
import com.muse.wprk_concept.presentation.MembershipHome
import com.muse.wprk_concept.presentation.ShowHome
import com.mwaibanda.virtualgroceries.Domain.Presentation.Navigation.SplashScreen
import dagger.hilt.android.AndroidEntryPoint
import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity(), OnAudioFocusChangeListener {
    @Inject lateinit var player: ExoPlayer
    @Inject lateinit var loudnessEnhancer: LoudnessEnhancer
    @Inject lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isPlaying by rememberSaveable { mutableStateOf(false) }
            var currentShow: Show? by rememberSaveable { mutableStateOf(null) }
            var isConnected by remember { mutableStateOf(false) }
            ConnectivityStatus(LocalContext.current).observe(LocalLifecycleOwner.current) {
                isConnected = it
            }

            WPRKEntry(
                player,
                isPlaying,
                isConnected,
                onPlayPauseClick = {
                    isPlaying = it
                }
            ) { navController, navPadding ->
                val backgroundColor = Color.Black
                NavHost(
                    navController = navController,
                    startDestination = SplashScreen.route,
                    modifier = Modifier
                        .background(Color.Black)
                        .padding(navPadding)
                ) {
                    composable(SplashScreen.route) {
                        SplashScreen(navController)
                    }
                    composable(ShowHome.route) {
                        ShowHome(
                            navController = navController,
                            gradient = backgroundColor,
                            showsViewModel = hiltViewModel(),
                            onShowClick = { currentShow = it },
                            onShowSetScheduleClick = { context, show ->
                                scheduleShow(context = context, show = show)
                            }
                        ) {
                            onSwitchMediaURL(it) {
                                isPlaying = true
                            }
                        }
                    }
                    composable(ShowDetail.route) { backStackEntry ->
                        currentShow?.let {
                            ShowDetail(
                                show = it,
                                gradient = backgroundColor
                            ) { context, show ->
                                scheduleShow(context = context, show = show)
                            }
                        }
                    }
                    composable(PodcastHome.route) {
                        PodcastHome(
                            navController = navController,
                            backgroundColor = backgroundColor,
                            podcastViewModel = hiltViewModel()
                        ) {
                            onSwitchMediaURL(it) {
                                isPlaying = true
                            }
                        }
                    }
                    composable(
                        PodcastDetail.route,
                        arguments = listOf(
                            navArgument("showID") { defaultValue = "" },
                            navArgument("imageURL") { defaultValue = "" },
                            navArgument("title") { defaultValue = "" },
                            navArgument("subTitle") { defaultValue = "" }
                        )) { backStackEntry ->

                        PodcastDetail(
                            navController = navController,
                            thumbnailURL = backStackEntry.arguments?.getString("imageURL"),
                            showID = backStackEntry.arguments?.getString("showID"),
                            title = backStackEntry.arguments?.getString("title"),
                            description = backStackEntry.arguments?.getString("subTitle"),
                            gradient = backgroundColor,
                            podcastViewModel = hiltViewModel<PodcastViewModel>()
                        ) {
                            onSwitchMediaURL(it) {
                                isPlaying = true
                            }
                        }

                    }

                    composable(Membership.route) {
                        MembershipHome(backgroundColor = backgroundColor) {
                            onSwitchMediaURL(it) {
                                isPlaying = true
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onSwitchMediaURL(
        URL: String,
        onCompletion: () -> Unit
    ) {
        player.apply {
            val dataSourceFactory = DefaultHttpDataSource.Factory()
            val sourceURL = MediaItem.Builder()
                .setUri(URL)
                .setLiveConfiguration(MediaItem.LiveConfiguration.Builder().build().apply {
                    setPlaybackSpeed(1.02f)
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(C.USAGE_MEDIA)
                            .setContentType(C.CONTENT_TYPE_MUSIC)
                            .build(),
                        true
                    )
                    setForegroundMode(true)
                })
                .build()

            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(sourceURL)
            setMediaSource(source)
            prepare()
            playWhenReady = true
        }

        onCompletion()
    }

    private fun scheduleShow(context: Context, show: Show) {
        val scheduleTime = getShowTimeInMillis(show)
        Log.d("SCH", "Scheduled Delay ${scheduleTime}")

        val scheduleShow = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(scheduleTime, TimeUnit.MILLISECONDS)
            .setInputData(
                workDataOf(
                    NotificationWorker.titleKey to show.title,
                    NotificationWorker.showIDKey to show.id
                )
            )
            .build()

        WorkManager.getInstance(this).enqueue(scheduleShow)
        Toast.makeText(context, "⏰ Reminder set for ${show.title}", Toast.LENGTH_SHORT).show()
    }

    private fun getShowTimeInMillis(show: Show): Long {
        val showDateTime = show.getShowDateTime(ShowTime.START)
        val now = LocalDateTime.now()

        Log.d(
            "SCH",
            "${showDateTime.dayOfMonth}/${showDateTime.monthValue}/${showDateTime.year}  ${showDateTime.hour}:${showDateTime.minute}"
        )
        return now.until(showDateTime, ChronoUnit.MILLIS)
    }

    override fun onResume() {
        super.onResume()
        audioManager.requestAudioFocus(
            this,
            STREAM_MUSIC,
            AUDIOFOCUS_GAIN
        )
    }

    override fun onAudioFocusChange(focusState: Int) {
        when (focusState) {
            AUDIOFOCUS_LOSS_TRANSIENT -> {
                player.pause()
            }
            AUDIOFOCUS_GAIN -> {
                player.play()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
        loudnessEnhancer.release()
    }
}
