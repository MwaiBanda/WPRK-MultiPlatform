package com.muse.wprk

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.ExoPlayer
import com.muse.wprk.core.utilities.ConnectivityStatus
import com.muse.wprk.core.utilities.ScreenConfigurations
import com.muse.wprk.presentation.navigation.BottomBar
import com.muse.wprk.presentation.navigation.ConnectivityWrapper
import com.muse.wprk.ui.theme.WPRK_conceptTheme
import com.muse.wprk.presentation.navigation.TopAppBar


@OptIn(ExperimentalAnimationApi::class, coil.annotation.ExperimentalCoilApi::class)
@Composable
fun WPRKEntry(
    player: ExoPlayer,
    isPlaying: Boolean,
    isConnected: Boolean,
    onPlayPauseClick: (Boolean) -> Unit,
    content: @Composable (NavHostController, PaddingValues) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setSystemBarsColor(color = Color.Black, darkIcons = useDarkIcons)
    }
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    WPRK_conceptTheme {
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    if (!ScreenConfigurations.screensWithoutTopAppBar.contains(currentRoute) && isConnected)
                        TopAppBar(navController = navController)
                },

                bottomBar = {
                    if (!ScreenConfigurations.screensWithoutBottomBar.contains(currentRoute) && isConnected)
                        BottomBar(navController, player, isPlaying, onPlayPauseClick)
                },
                content = {
                    ConnectivityWrapper(
                        isConnected = isConnected,
                        navController = navController,
                        paddingValues = it
                    ) { navController, innerPadding ->
                        content(navController, innerPadding)
                    }
                }
            )
        }
    }
}