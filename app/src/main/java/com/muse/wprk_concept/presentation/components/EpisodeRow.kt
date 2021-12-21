package com.muse.wprk_concept.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muse.wprk_concept.main.model.Episode

@Composable
fun EpisodeRow(onEpisodeClick: (String) -> Unit, episodeDTO: Episode) {
    Spacer(modifier = Modifier.height(10.dp))
    Column(Modifier.clickable { onEpisodeClick(episodeDTO.episodeURL) }) {
        Text(text = "EPISODE ${episodeDTO.number}", fontSize = 12.sp, color = Color.White)
        Text(text = episodeDTO.title, fontWeight = FontWeight.ExtraBold, color = Color.White)
        Text(
            text = episodeDTO.description
                .replace("<div>", "")
                .replace("</div>", ""),

            maxLines = 3,
            color = Color.White
        )
        Row {
            Icon(
                imageVector = Icons.Default.PlayCircleOutline,
                contentDescription = null,
                tint = Color.White
            )
            Text(text = episodeDTO.duration, color = Color.White)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}