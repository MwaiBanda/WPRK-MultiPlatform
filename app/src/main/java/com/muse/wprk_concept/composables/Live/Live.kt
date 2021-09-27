package com.muse.wprk_concept.composables

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
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
import com.muse.wprk_concept.composables.Live.LiveViewModel
import java.util.*
import androidx.compose.runtime.getValue
import coil.transform.RoundedCornersTransformation
import com.muse.wprk_concept.data.*

@Composable
fun Live(gradient: Brush, liveViewModel: LiveViewModel) {
    var days = remember { mutableStateListOf (
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    )}
    val lifecycle = LocalLifecycleOwner.current
    val showState = rememberLazyListState()
    val scheduleState = rememberLazyListState()
    val mainColumnState = rememberLazyListState()
    val columnState = rememberLazyListState()
    var shows = remember { mutableStateListOf<Show>()}
    var scheduledShows = remember { mutableStateListOf<Show>()}
    var currentDay by remember { mutableStateOf(0) }
    var selectedDate = remember { mutableStateOf(liveViewModel.currentDay()) }
    liveViewModel.selectedDate.observe(lifecycle){ newValue ->
        currentDay = newValue
    }
    liveViewModel.shows.observe(lifecycle){ newShows ->
        shows.swapList(newShows)
        scheduledShows.swapList(shows.filter { it.getFormattedDate(showTime = ShowTime.START) == selectedDate.value })
    }
    LaunchedEffect(key1 = false){
        val calender = Calendar.getInstance()
        val intDay = calender.get(Calendar.DAY_OF_WEEK)
        val currentDate = days[intDay - 1]
        while(currentDate != days.first()) {
            days.add(days.lastIndex, days.removeAt(0))
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
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Shows", fontWeight = FontWeight.ExtraBold, fontSize = 40.sp, color = Color.White)
                Spacer(modifier = Modifier.fillMaxWidth(0.6f))
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
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                                append("91.5")
                            }
                            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, fontSize = 13.sp)) {
                                append("FM")
                            } },
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                }
            }
            Text(text = "Currently Scheduled Today ", color = Color.White)
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
            Text(text = "Schedule", fontWeight = FontWeight.ExtraBold, fontSize = 40.sp,  color = Color.White)
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            LazyRow(state = scheduleState, modifier = Modifier.fillMaxWidth()) {
                itemsIndexed((0..6).toList()) { i, item ->

                    if (i != 0) Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            liveViewModel.onSelectedChange(i)
                            selectedDate = when (i) {
                                0 -> {
                                    mutableStateOf(liveViewModel.currentDay())
                                }
                                else -> {
                                    mutableStateOf(liveViewModel.getDayByOffset(i.toLong()))
                                }
                            }
                            scheduledShows.swapList(shows.filter { it.getFormattedDate(showTime = ShowTime.START) == selectedDate.value })
                            Log.d("MAIN", "[SELECTED] ${selectedDate}")
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.Gray)
                                .width(90.dp)
                                .height(90.dp)
                        )
                        Text(text = days[item],  color = Color.White)
                    }

                    if (i == 6) Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
        item {

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Scheduled Shows", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = "Listings", fontWeight = FontWeight.Bold, color = Color.White)

        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                state = columnState,
                modifier = Modifier.height(300.dp)
            ) {
                items(scheduledShows) { item ->
                    ScheduleUnit(title = "${item.title}", category = "${item.category}", time = "${item.getTime(showTime = ShowTime.START)}")
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun ScheduleUnit(title: String, category: String, time: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold)
                Text(text = "$category", color = Color.White, fontWeight = FontWeight.Normal)
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.size(20.dp),
                ) {
                    Icon(
                        Icons.Default.Alarm,
                        contentDescription = "Access alarm",
                        tint = Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))

                Text(text = time, fontWeight = FontWeight.Normal, color = Color.White)
            }

        }
    }
}

@Preview
@Composable
fun Preview() {
    val gradient = Brush.verticalGradient(listOf(Color.Black,  Color.LightGray))
    Live(gradient = gradient, liveViewModel = hiltViewModel<LiveViewModel>())
}
fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
    clear()
    addAll(newList)
}