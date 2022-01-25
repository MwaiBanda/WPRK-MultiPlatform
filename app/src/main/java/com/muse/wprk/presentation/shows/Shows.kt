package com.muse.wprk_concept.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.muse.wprk.core.utilities.ShowTime
import com.muse.wprk.main.model.Show
import com.muse.wprk.main.model.getFormattedDate
import com.muse.wprk.presentation.components.LiveButton
import com.muse.wprk.presentation.shows.LiveViewModel
import com.muse.wprk.presentation.shows.ScheduledShows
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun ShowsHome(
    gradient: Color,
    showsViewModel: LiveViewModel,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onSwitchToDefault: (String) -> Unit
) {
    var days = remember {
        mutableStateListOf(
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
        )
    }
    val lifecycle = LocalLifecycleOwner.current
    val showState = rememberLazyListState()
    val scheduleState = rememberLazyListState()
    val mainColumnState = rememberLazyListState()
    val columnState = rememberLazyListState()
    var shows = remember { mutableStateListOf<Show>() }
    var scheduledShows = remember { mutableStateListOf<Show>() }

    var currentDayString by remember { mutableStateOf("") }
    var selectedDate = remember { mutableStateOf(showsViewModel.currentDay()) }
    var selectedDateString by remember {
        mutableStateOf("")
    }
    var empty =  listOf(
            Show(
                0,
                "WPRK",
                "",
                0,
                "2018-07-22T14:00:00-03:00",
                "",
                "",
                "2018-07-22T14:00:00-03:00",
                "",
                "Nothing Scheduled",
                ""

            )
        )



    showsViewModel.shows.observe(lifecycle) { newShows ->
        shows.swapList(newShows)
    }
    LaunchedEffect(key1 = false) {
        val calender = Calendar.getInstance()
        val intDay = calender.get(Calendar.DAY_OF_WEEK)
        currentDayString = days[intDay - 1]
        while (currentDayString != days.first()) {
            days.add(days.lastIndex, days.removeAt(0))
        }
        selectedDateString = showsViewModel.currentDay().toString()
        showsViewModel.getShows {
            scheduledShows.addAll(shows.filter { it.getFormattedDate(ShowTime.START) == selectedDate.value })
        }
    }

    LazyColumn(
        state = mainColumnState,
        modifier = Modifier
            .background(gradient)
            .fillMaxSize()
            .padding(start = 10.dp),
    ) {

        item {
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Shows",
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.h5,
                    color = Color.White
                )
                LiveButton(onSwitchToDefault)
            }
            Text(text = "Currently Scheduled Today ", color = Color.Gray)
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            LazyRow(state = showState, modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(shows) { i, show ->


                    if (i != 0) Spacer(modifier = Modifier.width(10.dp))
                    Box {
                        Image(
                            painter = rememberImagePainter(
                                data = show.image,
                                onExecute = { _, _ -> true },
                                builder = {
                                    transformations(RoundedCornersTransformation(10f))
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .width(175.dp)
                                .height(180.dp)
                        )
                    }
                    if (show == shows.last()) Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Schedule",
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
            Text(text = "Currently Scheduled Today ", color = Color.Gray)
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))
        }


        item {
            LazyRow(state = scheduleState, modifier = Modifier.fillMaxWidth()) {
                itemsIndexed((0..6).toList()) { i, item ->
                    if (i != 0) Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            coroutineScope.launch {
                                showsViewModel.onSelectedChange(i)
                                selectedDate = when (i) {
                                    0 -> {
                                        mutableStateOf(showsViewModel.currentDay())
                                    }
                                    else -> {
                                        mutableStateOf(showsViewModel.getDayByOffset(i.toLong()))
                                    }
                                }
                                currentDayString = days[i]
                                selectedDateString = selectedDate.value.toString()
                                scheduledShows.clear()
                                scheduledShows.addAll(shows.filter { it.getFormattedDate(ShowTime.START) == selectedDate.value })
                                Log.d("MAIN", "[SHOWS] ${scheduledShows}")
                                Log.d("MAIN", "[SELECTED] $selectedDateString")
                            }
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(color = Color.parse("#ffafcc"))
                                .width(90.dp)
                                .height(90.dp)
                                .border(1.dp, color = Color.White, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = days[item].take(3),
                                color = Color.White,
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Text(text = days[item], color = Color.White)
                    }

                    if (i == 6) Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }


        item {
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(5.dp))
            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Scheduled",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    style = MaterialTheme.typography.h5
                )
                Text(text = currentDayString, color = Color.Gray)

            }
            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Listings",
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Gray,
                    style = MaterialTheme.typography.h6
                )
                Text(text = selectedDateString, color = Color.Gray, style = MaterialTheme.typography.caption)
            }

        }
        item {
            ScheduledShows(list = if (scheduledShows.isEmpty()) empty else scheduledShows)
        }
    }
}

@Preview
@Composable
fun Preview() {
    val gradient = Brush.verticalGradient(listOf(Color.Black, Color.LightGray))
    ShowsHome(gradient = Color.Black, showsViewModel = hiltViewModel<LiveViewModel>()) {

    }
}

fun <T> SnapshotStateList<T>.swapList(newList: List<T>) {
    clear()
    addAll(newList)
}