package com.muse.wprk_concept.main

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookOnline
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.ui.graphics.vector.ImageVector
import com.muse.wprk_concept.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector, val tabName: String? = null) {
    object Live : Screen(route ="live",resourceId = R.string.live, icon = Icons.Default.LiveTv, tabName = "Home")
    object Podcasts : Screen(route ="podcasts",resourceId = R.string.podcasts, icon = Icons.Default.BookOnline, tabName = "Podcasts")
    object PodcastDetail : Screen(route ="podcastDetail/{showID}",resourceId = R.string.podcasts, icon = Icons.Default.BookOnline, "Podcasts")
    object Account : Screen(route ="account",resourceId = R.string.account, icon = Icons.Default.PersonAdd, tabName = "Account")
    object PlayerScreen: Screen(route ="playerScreen",resourceId = R.string.player_view, icon = Icons.Default.PersonAdd)
    object PlayerDetail: Screen(route ="playerDetail",resourceId = R.string.player_detail, icon = Icons.Default.PersonAdd)

}