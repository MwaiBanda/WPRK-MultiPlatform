package com.muse.wprk_concept.core

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookOnline
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.ui.graphics.vector.ImageVector
import com.muse.wprk_concept.R

sealed class NavigationRoutes(
    val route: String,
    @StringRes val resourceId: Int? = null,
    val icon: ImageVector? = null,
    val tabName: String? = null
) {
    object Live : NavigationRoutes(route ="live", resourceId = R.string.live, icon = Icons.Default.LiveTv, tabName = "Home")
    object Podcasts : NavigationRoutes(route ="podcasts", resourceId = R.string.podcasts, icon = Icons.Default.BookOnline, tabName = "Podcasts")
    object PodcastDetail : NavigationRoutes(route ="podcastDetail/{showID}/{imageURL}/{title}/{subTitle}",resourceId = R.string.podcasts, icon = Icons.Default.BookOnline, "Podcasts")
    object Account : NavigationRoutes(route ="account", resourceId = R.string.account, icon = Icons.Default.PersonAdd, tabName = "Account")
    object PlayerScreen: NavigationRoutes(route ="playerScreen")
    object SplashScreen: NavigationRoutes(route ="splashScreen")
}