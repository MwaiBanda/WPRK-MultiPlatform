package com.muse.wprk_concept

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.muse.wprk_concept.core.NavigationRoutes.*
import com.muse.wprk_concept.core.utilities.Constants.DEFAULT_STREAM
import com.muse.wprk_concept.main.PodcastHome
import com.muse.wprk_concept.presentation.Account
import com.muse.wprk_concept.presentation.Live
import com.muse.wprk_concept.presentation.components.PlayerView
import com.muse.wprk_concept.presentation.podcasts.PodcastDetail
import com.muse.wprk_concept.presentation.podcasts.PodcastViewModel
import com.muse.wprk_concept.presentation.shows.LiveViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WPRKEntry { navController, navPadding, player, context, gradient ->
                NavHost(
                    navController = navController,
                    startDestination = Live.route,
                    modifier = Modifier
                        .background(Color.Black)
                        .padding(navPadding)
                ) {
                    composable(Live.route) {
                        val context = context.current
                        Live(gradient = gradient, liveViewModel = hiltViewModel<LiveViewModel>()) {
                            switchToDefault(context, player)
                        }
                    }
                    composable(Podcasts.route) {
                        val context = context.current
                        PodcastHome(navController = navController, gradient = gradient, podcastViewModel = hiltViewModel<PodcastViewModel>(), onSwitchToDefault = {
                            switchToDefault(context, player)
                        }){ episodeURL ->
                            switchToURL(episodeURL, context, player)
                        }
                    }
                    composable(Account.route) { Account(gradient = gradient) }
                    composable(PlayerScreen.route){ PlayerView(player = player, context = context) }
                    composable(
                            PodcastDetail.route,
                            arguments = listOf(
                                navArgument("showID"){ defaultValue = "" },
                                navArgument("imageURL"){ defaultValue = ""},
                                navArgument("title"){ defaultValue = ""},
                                navArgument("subTitle"){ defaultValue = ""}
                            )){ backStackEntry ->

                        val context = context.current
                        PodcastDetail(
                                navController = navController,
                                thumbnailURL = backStackEntry.arguments?.getString("imageURL"),
                                showID = backStackEntry.arguments?.getString("showID"),
                                title = backStackEntry.arguments?.getString("title"),
                                description = backStackEntry.arguments?.getString("subTitle"),
                                gradient = gradient,
                                podcastViewModel = hiltViewModel<PodcastViewModel>()
                        ) { episodeURL ->
                            switchToURL(episodeURL, context, player)
                        }
                    }
                }
            }

        }

    }
    private fun switchToDefault(context: Context, player: SimpleExoPlayer) {
        player.apply {
            val dataSourceFactory = DefaultDataSourceFactory(
                context, Util.getUserAgent(
                    context,
                    context.packageName
                )
            )

            val sourceURL = MediaItem.Builder()
                .setUri(DEFAULT_STREAM)
                .setLiveConfiguration(MediaItem.LiveConfiguration.Builder().build().apply {
                    setPlaybackSpeed(1.02f)
                    setHandleAudioBecomingNoisy(true)
                })
                .build()

            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(sourceURL)

            setMediaSource(source)
            setHandleAudioBecomingNoisy(true)
            prepare()
        }
    }
    private fun switchToURL(URL: String, context: Context, player: SimpleExoPlayer) {
        player.apply {
            val dataSourceFactory = DefaultDataSourceFactory(
                context, Util.getUserAgent(
                    context,
                    context.packageName
                )
            )

            val sourceURL = MediaItem.Builder()
                .setUri(URL)
                .setLiveConfiguration(MediaItem.LiveConfiguration.Builder().build().apply {
                    setPlaybackSpeed(1.02f)
                    setHandleAudioBecomingNoisy(true)
                })
                .build()

            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(sourceURL)

            setMediaSource(source)
            setHandleAudioBecomingNoisy(true)
            prepare()
        }
    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {


}

