package com.muse.wprk.presentation.shows

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.muse.wprk.core.utilities.ShowTime
import com.muse.wprk.main.model.Show
import com.muse.wprk_concept.presentation.ScheduleUnit

@Composable
fun ScheduledShows(list: List<Show>, onShowSetScheduleClick: (Context, Show) -> Unit) {
    val context = LocalContext.current
    Column(Modifier.fillMaxWidth()) {
        list.forEach { show ->
            if (show.id == list.first().id) {
                Spacer(modifier = Modifier.height(5.dp))
                Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))
            }
            ScheduleUnit(
                title = show.title,
                category = if (show.category == "unset") "WPRK" else show.category ?: "WPRK",
                time = show.getTime(showTime = ShowTime.START).toString(),
                isLast = { list.last().id == show.id },
                onShowSetScheduleClick = { onShowSetScheduleClick(context, show) }
            )
            if (show.id != list.last().id) {
                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))

            }
        }
    }
}