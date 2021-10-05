package com.muse.wprk_concept.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import com.muse.wprk_concept.data.Show
import com.muse.wprk_concept.data.ShowTime
import com.muse.wprk_concept.data.getFormattedDate
import com.muse.wprk_concept.data.getTime
import com.muse.wprk_concept.parse
import com.muse.wprk_concept.screens.Live.LiveViewModel
import java.util.*
import com.muse.wprk_concept.screens.ScheduleUnit as ScheduleUnit1

@Composable
fun Live(gradient: Brush, liveViewModel: LiveViewModel, onSwitchToDefault: () -> Unit) {
    var days = remember { mutableStateListOf (
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    )}
    var abrev_days = remember { mutableStateListOf (
        "Sun", "Mon", "Tue", "Wed", "Th", "Fri", "Sat"
    ) }
    val lifecycle = LocalLifecycleOwner.current
    val showState = rememberLazyListState()
    val scheduleState = rememberLazyListState()
    val mainColumnState = rememberLazyListState()
    val columnState = rememberLazyListState()
    var shows = remember { mutableStateListOf<Show>()}
    var scheduledShows = remember { mutableStateListOf<Show>()}
    var currentDay by remember { mutableStateOf(0) }
    var selectedDate = remember { mutableStateOf(liveViewModel.currentDay()) }
    var selectedDateString by remember {
        mutableStateOf("")
    }
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
                Spacer(modifier = Modifier.fillMaxWidth(0.53f))
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
                                .offset(y = 2.dp)
                        )
                    }
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
                            selectedDateString = selectedDate.value.toString()
                            scheduledShows.swapList(shows.filter { it.getFormattedDate(showTime = ShowTime.START) == selectedDate.value })
                            Log.d("MAIN", "[SELECTED] ${selectedDate}")
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
                            Text(text = abrev_days[item], color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                        }
                        Text(text = days[item],  color = Color.White)
                    }

                    if (i == 6) Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
        item {

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Scheduled Shows", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Row {
                Text(text = "Listings", fontWeight = FontWeight.Bold, color = Color.White)
                Spacer(modifier = Modifier.fillMaxWidth())
                Text(text = selectedDateString, fontWeight = FontWeight.Bold, color = Color.White)
            }

        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                state = columnState,
                modifier = Modifier.height(300.dp)
            ) {
                items(scheduledShows) { item ->
                    ScheduleUnit1(title = "${item.title}", category = "${item.category}", time = "${item.getTime(showTime = ShowTime.START)}")
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Preview
@Composable
fun Preview() {
    val gradient = Brush.verticalGradient(listOf(Color.Black,  Color.LightGray))
    Live(gradient = gradient, liveViewModel = hiltViewModel<LiveViewModel>()) {

    }
}
fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
    clear()
    addAll(newList)
}