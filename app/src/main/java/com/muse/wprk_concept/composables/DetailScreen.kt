package com.muse.wprk_concept.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.android.exoplayer2.SimpleExoPlayer
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailScreen(player: SimpleExoPlayer) {
    val window by remember {
        mutableStateOf(0)
    }
    val position by remember {
        mutableStateOf(0L)
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(imageVector = Icons.Filled.Photo, 
            contentDescription = "", 
            modifier = Modifier.size(width = 50.dp, height = 50.dp))
        Spacer(modifier = Modifier.height(35.dp))
        LinearProgressIndicator(progress = player.currentPosition.toFloat())
    }
    player.seekTo(window, position)
}