package com.muse.wprk

import android.content.Context
import android.media.AudioManager
import android.media.AudioManager.*
import android.media.audiofx.LoudnessEnhancer
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarDuration
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
import com.muse.wprk.core.exts.getShowDateTime
import com.muse.wprk.core.utilities.ConnectivityStatus
import com.muse.wprk.core.utilities.NavigationRoutes.*
import com.muse.wprk.core.utilities.NotificationWorker
import com.muse.wprk.core.utilities.ShowTime
import com.muse.wprk.main.PodcastHome
import com.mwaibanda.wprksdk.main.model.Show
import com.muse.wprk.presentation.podcasts.PodcastDetail
import com.muse.wprk.presentation.podcasts.PodcastViewModel
import com.muse.wprk.presentation.shows.ShowDetail
import com.muse.wprk.presentation.MembershipHome
import com.muse.wprk.presentation.ShowHome
import com.mwaibanda.virtualgroceries.Domain.Presentation.Navigation.SplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.threeten.bp.LocalDateTime
import org.threeten.bp.temporal.ChronoUnit
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.math.log10
import kotlin.math.roundToInt


@AndroidEntryPoint
class MainActivity : ComponentActivity(), OnAudioFocusChangeListener {
    @Inject
    lateinit var player: ExoPlayer
    @Inject
    lateinit var loudnessEnhancer: LoudnessEnhancer
    @Inject
    lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentShow: Show? by rememberSaveable { mutableStateOf(null) }
            var isPlaying by rememberSaveable { mutableStateOf(false) }
            var isConnected by rememberSaveable { mutableStateOf(false) }
            var snackbarMessage by rememberSaveable { mutableStateOf("Hello") }
            var coroutineScope = rememberCoroutineScope()
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
            ) { navController, scaffoldState, navPadding ->
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
                            background = backgroundColor,
                            showsViewModel = hiltViewModel(),
                            onShowClick = { currentShow = it },
                            onShowSetScheduleClick = { context, show ->
                                scheduleShow(context = context, show = show) {
                                    coroutineScope.launch {
                                        snackbarMessage = "⏰ Reminder set for ${show.title}"
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            snackbarMessage,
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
                            }
                        ) {
                            onSwitchMediaURL(it) {
                                isPlaying = true
                            }
                        }
                    }
                    composable(ShowDetail.route) {
                        currentShow?.let {
                            ShowDetail(
                                show = it,
                                gradient = backgroundColor
                            ) { context, show ->
                                scheduleShow(context = context, show = show) {
                                    coroutineScope.launch {
                                        snackbarMessage = "⏰ Reminder set for ${show.title}"
                                        scaffoldState.snackbarHostState.showSnackbar(
                                            snackbarMessage,
                                            duration = SnackbarDuration.Short
                                        )
                                    }
                                }
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
                        MembershipHome {
                            onSwitchMediaURL(it) {
                                isPlaying = true
                            }
                        }
                    }
                }
            }
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        audioManager.requestAudioFocus(
            this,
            STREAM_MUSIC,
            AUDIOFOCUS_GAIN
        )
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
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(C.USAGE_MEDIA)
                            .setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
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
        }.also { enhanceAudio() }

        onCompletion()
    }

    private fun scheduleShow(context: Context, show: Show, onCompletion: () -> Unit) {
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

        WorkManager.getInstance(context).enqueue(scheduleShow)
//        Toast.makeText(context, "⏰ Reminder set for ${show.title}", Toast.LENGTH_SHORT).show()
        onCompletion()
    }

    private fun getShowTimeInMillis(show: Show): Long {
        val showDateTime = show.getShowDateTime(ShowTime.START)
        val now = LocalDateTime.now()
        Log.d(
            "SCH",
            """
             Schedule Time: ${showDateTime.dayOfMonth}/${showDateTime.monthValue}/${showDateTime.year}  ${showDateTime.hour}:${showDateTime.minute}
               Time Now: ${now.dayOfMonth}/${now.monthValue}/${now.year}  ${now.hour}:${now.minute}
            """
        )
        return now.until(showDateTime, ChronoUnit.MILLIS)
    }

    override fun onResume() {
        super.onResume()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        enhanceAudio()

    }

    override fun onPause() {
        super.onPause()
        window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    override fun onRestart() {
        super.onRestart()
        enhanceAudio()
    }

    private fun enhanceAudio() {
        loudnessEnhancer = LoudnessEnhancer(player.audioSessionId).apply {
            val audioPct = 1.2
            val gainMB = (log10(audioPct) * 10000).roundToInt()
            setTargetGain(gainMB)
            enabled = true
        }
    }
    override fun onAudioFocusChange(focusState: Int) {
        when (focusState) {
            AUDIOFOCUS_LOSS_TRANSIENT -> {}
            AUDIOFOCUS_GAIN -> {
                player.play()
                enhanceAudio()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
        loudnessEnhancer.release()
    }
}