package com.muse.wprk.core.utilities

import com.muse.wprk.core.utilities.NavigationRoutes.*

object ScreenConfigurations {
    val screensWithBackButton: List<String> = listOf(
        ShowDetail.route.take(7),
        PodcastDetail.route.take(7),
    )
}
