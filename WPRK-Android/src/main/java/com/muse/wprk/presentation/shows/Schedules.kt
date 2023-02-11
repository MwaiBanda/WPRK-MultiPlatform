package com.muse.wprk.presentation.shows

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.muse.wprk.core.exts.getTime
import com.muse.wprk.core.utilities.ShowTime
import com.mwaibanda.wprksdk.main.model.Show
import com.muse.wprk.presentation.ScheduleUnit

@Composable
fun ScheduledShows(
    list: List<Show>,
    onShowSetScheduleClick: (Context, Show) -> Unit,
    onShowSelectedClick: (Show) -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 285.dp)
    ) {
        list.forEach { show ->
            ScheduleUnit(
                title = show.title,
                category = if (show.category == "unset") "WPRK" else show.category ?: "WPRK",
                time = show.getTime(showTime = ShowTime.START),
                onShowSelectedClick = { onShowSelectedClick(show) },
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