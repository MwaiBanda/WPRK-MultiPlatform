package com.muse.wprk_concept.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Podcasts
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.muse.wprk_concept.core.utilities.ShowTime
import com.muse.wprk_concept.main.model.Show
import com.muse.wprk_concept.main.model.getFormattedDate
import com.muse.wprk_concept.presentation.shows.LiveViewModel
import com.muse.wprk_concept.presentation.shows.ScheduledShows
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun Live(
    gradient: Color,
    liveViewModel: LiveViewModel,
    coroutineScope: CoroutineScope = rememberCoroutineScope() ,
    onSwitchToDefault: () -> Unit) {
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
    var selectedDate = remember { mutableStateOf(liveViewModel.currentDay()) }
    var selectedDateString by remember {
        mutableStateOf("")
    }
    var empty =  listOf(
            Show(
                0,
                "WPRK",
                "",
                0,
                "00:00",
                "",
                "",
                "00:00",
                "",
                "Nothing Scheduled",
                ""

            )
        )



    liveViewModel.shows.observe(lifecycle) { newShows ->
        shows.swapList(newShows)
    }
    LaunchedEffect(key1 = false) {
        val calender = Calendar.getInstance()
        val intDay = calender.get(Calendar.DAY_OF_WEEK)
        currentDayString = days[intDay - 1]
        while (currentDayString != days.first()) {
            days.add(days.lastIndex, days.removeAt(0))
        }
        selectedDateString = liveViewModel.currentDay().toString()
        liveViewModel.getShows {
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .border(
                            BorderStroke(
                                1.dp,
                                color = Color.White
                            ), RoundedCornerShape(10.dp)
                        )
                        .size(width = 100.dp, height = 43.dp)

                ) {
                    Row(Modifier.clickable { onSwitchToDefault() }) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                                    append("91.5")
                                }
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 13.sp
                                    )
                                ) {
                                    append("FM")
                                }
                            },
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            imageVector = Icons.Filled.Podcasts,
                            contentDescription = "",
                            tint = Color.Red,
                            modifier = Modifier
                                .size(18.dp, 18.dp)
                                .offset(y = 5.dp)
                        )
                    }
                }
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
                                liveViewModel.onSelectedChange(i)
                                selectedDate = when (i) {
                                    0 -> {
                                        mutableStateOf(liveViewModel.currentDay())
                                    }
                                    else -> {
                                        mutableStateOf(liveViewModel.getDayByOffset(i.toLong()))
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
    Live(gradient = Color.Black, liveViewModel = hiltViewModel<LiveViewModel>()) {

    }
}

fun <T> SnapshotStateList<T>.swapList(newList: List<T>) {
    clear()
    addAll(newList)
}