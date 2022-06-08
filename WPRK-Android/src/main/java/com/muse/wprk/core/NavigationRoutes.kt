package com.muse.wprk.core

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.BookOnline
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.ui.graphics.vector.ImageVector
import com.muse.wprk.R

sealed class NavigationRoutes(
    val route: String,
    @StringRes val resourceId: Int? = null,
    val icon: ImageVector? = null,
) {
    object ShowHome : NavigationRoutes(route ="shows", resourceId = R.string.shows, icon = Icons.Outlined.DateRange)
    object ShowDetail : NavigationRoutes(route ="showDetail")
    object PodcastHome : NavigationRoutes(route ="podcasts", resourceId = R.string.podcasts, icon = Icons.Outlined.BookOnline)
    object PodcastDetail : NavigationRoutes(route ="podcastDetail/{showID}/{imageURL}/{title}/{subTitle}", icon = Icons.Outlined.BookOnline)
    object Membership: NavigationRoutes(route ="membership", resourceId = R.string.membership, icon = Icons.Outlined.AlternateEmail)
    object PlayerScreen: NavigationRoutes(route ="playerScreen")
    object SplashScreen: NavigationRoutes(route ="splashScreen")
}