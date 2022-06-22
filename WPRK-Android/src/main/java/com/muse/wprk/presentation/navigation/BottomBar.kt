package com.muse.wprk.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.android.exoplayer2.ExoPlayer
import com.muse.wprk.core.utilities.NavigationRoutes
import com.muse.wprk.presentation.components.WPRKPlayer
import kotlinx.coroutines.delay

@Composable
fun BottomBar(navController: NavController,  player: ExoPlayer, isPlaying: Boolean, onPlayPauseClick: (Boolean) -> Unit) {
    val bottomTabs = listOf(
        NavigationRoutes.ShowHome,
        NavigationRoutes.PodcastHome,
        NavigationRoutes.Membership
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    var isVisible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit){
        delay(50)
        isVisible = true
    }
    Column {
        if (isVisible)
            WPRKPlayer(player = player, isPlaying = isPlaying, onPlayPauseClick)
            BottomNavigation(
                backgroundColor = Color.Black,
                contentColor = Color.White
            ) {


                bottomTabs.forEach { tab ->
                    BottomNavigationItem(
                        label = {
                            Text(text = LocalContext.current.getString(tab.resourceId ?: 0))
                        },
                        icon = {
                            Icon(
                                tab.icon ?: Icons.Default.PersonAdd,
                                "Tab Icon"
                            )
                        },
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
}