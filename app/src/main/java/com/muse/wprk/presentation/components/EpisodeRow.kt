package com.muse.wprk.presentation.components

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
import com.muse.wprk.main.model.Episode

@Composable
fun EpisodeRow(onEpisodeClick: (String) -> Unit, episode: Episode) {
    Spacer(modifier = Modifier.height(10.dp))
    Column(Modifier.clickable { onEpisodeClick(episode.episodeURL) }) {
        Text(text = "EPISODE ${episode.number}", fontSize = 12.sp, color = Color.White)
        Text(text = episode.title, fontWeight = FontWeight.ExtraBold, color = Color.White)
        ExpandableText(text = episode.description
            .replace("<div>", "")
            .replace("</div>", ""), minimizedMaxLines = 4)

        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Icon(
                imageVector = Icons.Default.PlayCircleOutline,
                contentDescription = null,
                tint = Color.White
            )
            Text(text = episode.duration, color = Color.White)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}