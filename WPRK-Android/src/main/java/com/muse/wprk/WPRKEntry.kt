package com.muse.wprk

import android.content.Context
import android.media.audiofx.Equalizer
import android.media.audiofx.LoudnessEnhancer
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.MimeTypes
import com.muse.wprk.core.NavigationRoutes
import com.muse.wprk.core.utilities.Constants
import com.muse.wprk.presentation.components.WPRKPlayer
import com.muse.wprk.ui.theme.WPRK_conceptTheme


@OptIn(ExperimentalAnimationApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun WPRKEntry(
    isPlaying: Boolean,
    onPlayerEnabled: (ExoPlayer) -> Unit,
    onPlayPauseClick: (Boolean) -> Unit,
    content: @Composable (NavHostController, PaddingValues, ExoPlayer, ProvidableCompositionLocal<Context>) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setSystemBarsColor(color = Color.Black, darkIcons = useDarkIcons)
    }
    val scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val bottomTabs = listOf(
        NavigationRoutes.Live,
        NavigationRoutes.Podcasts,
        NavigationRoutes.Account
    )

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext
    val current = context.current
    val player = remember {

        val player = ExoPlayer.Builder(current)
            .build().apply {

            val dataSourceFactory = DefaultHttpDataSource.Factory()

            val media = MediaItem.Builder()
                .setUri(Constants.DEFAULT_STREAM)
                .setLiveConfiguration(MediaItem.LiveConfiguration.Builder().build().apply {
                    setPlaybackSpeed(1.02f)
                    setAudioAttributes(AudioAttributes.Builder()
                        .setUsage(C.USAGE_MEDIA)
                        .setContentType(C.CONTENT_TYPE_MUSIC)
                        .build(),
                        true
                    )
                    setForegroundMode(true)
                })
                .build()




            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(media)

            setMediaSource(source)
            prepare()
            onPlayerEnabled(this)
        }

        player

    }


    WPRK_conceptTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    if (currentRoute != NavigationRoutes.SplashScreen.route)
                    TopAppBar(
                        title = {
                            if (currentRoute?.take(13) != NavigationRoutes.PodcastDetail.route.take(13))
                                Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center ) {
                                    Image(painter = rememberImagePainter(
                                        data = Constants.LOGO_URL,
                                        onExecute = { _, _ -> true },
                                        builder = {
                                            crossfade(true)
                                        }
                                    ),
                                        modifier = Modifier
                                            .size(50.dp)
                                            .offset(x = (-35).dp),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = null
                                    )
                                }
                        },
                        navigationIcon = {
                            if (currentRoute?.take(13) == NavigationRoutes.PodcastDetail.route.take(13)) {
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
                                    Row {
                                        Icon(Icons.Filled.ArrowBackIosNew, "")
                                        Text(text = "Back", color = Color.White)
                                    }
                                }
                            }
                        },
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    ) },

                bottomBar = {
                    if (currentRoute != NavigationRoutes.SplashScreen.route)
                    Column {


                        WPRKPlayer(player = player, isPlaying = isPlaying, onPlayPauseClick)
                        BottomNavigation(
                            backgroundColor = Color.Black,
                            contentColor = Color.White
                        ) {


                            bottomTabs.forEach { tab ->
                                BottomNavigationItem(
                                    icon = { Icon(tab.icon ?: Icons.Default.PersonAdd, "") },
                                    selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true,
                                    onClick = {
                                        navController.navigate(tab.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }

                        }
                    }
                },
                content = {
                    content(navController, it, player, context)
                }
            )
        }
    }
}