package com.muse.wprk_concept.composables.Podcasts
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun PodcastDetail(navController: NavHostController, showID: String?, podcastViewModel: PodcastViewModel){
    Column {
        Text(text = showID ?: "Undefined")

    }
}