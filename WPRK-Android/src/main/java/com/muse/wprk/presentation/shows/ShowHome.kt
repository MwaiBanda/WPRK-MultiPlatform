package com.muse.wprk.presentation

import android.content.Context
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.muse.wprk.core.utilities.ShowTime
import com.mwaibanda.wprksdk.main.model.Show
import com.muse.wprk.presentation.components.LiveButton
import com.muse.wprk.presentation.shows.ShowViewModel
import com.muse.wprk.presentation.shows.ScheduledShows
import com.mwaibanda.wprksdk.main.model.Episode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.*

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ShowHome(
    navController: NavController,
    gradient: Color,
    showsViewModel: ShowViewModel,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onShowClick: (Show) -> Unit,
    onShowSetScheduleClick: (Context, Show) -> Unit,
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
    val shows = remember { mutableStateListOf<Show>() }
    val scheduledShows = remember { mutableStateListOf<Show>() }
    showsViewModel.scheduledShows.observe(lifecycle) {
        scheduledShows.clear()
        scheduledShows.addAll(it)
    }
    var currentDayString by remember { mutableStateOf("") }
    var selectedDate = remember { mutableStateOf(showsViewModel.currentDay()) }
    var selectedDateString by remember {
        mutableStateOf("")
    }

    val empty = listOf(
        Show(
            id = 0,
            category = "WPRK",
            description = "",
            duration = 0,
            end = "2018-07-22T14:00:00-03:00",
            image = "",
            since = "",
            start = "2018-07-22T14:00:00-03:00",
            timezone = "",
            title = "Nothing Scheduled",
            url = ""
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
            showsViewModel.onScheduleChange(selectedDate.value)
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
            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Shows",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.h5,
                        color = Color.White
                    )
                    Text(text = "Tap Scheduled Shows ", color = Color.Gray)
                }
                LiveButton(Modifier.offset(y= 3.dp), onSwitchToDefault)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            LazyRow(state = showState, modifier = Modifier.fillMaxWidth()) {
                if (shows.isEmpty()) {
                    itemsIndexed(MutableList(12){ return@MutableList 0 }) { i, _ ->
                        if (i != 0) Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .width(175.dp)
                                .height(180.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Gray.copy(0.3f))
                        )
                    }
                } else {
                    itemsIndexed(shows) { i, show ->
                        var imageURL =
                            URLEncoder.encode(show.image, StandardCharsets.UTF_8.toString())
                        if (i != 0) Spacer(modifier = Modifier.width(10.dp))
                        Box {
                            Image(
                                painter = rememberAsyncImagePainter(
                                    ImageRequest.Builder(LocalContext.current)
                                        .data(data = show.image)
                                        .apply(block = fun ImageRequest.Builder.() {
                                            transformations(RoundedCornersTransformation(10f))
                                        }).build()
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .clickable {
                                        onShowClick(show)
                                        navController.navigate("sDetail")
                                    }
                                    .width(175.dp)
                                    .height(180.dp)
                            )
                        }
                        if (show == shows.last()) Spacer(modifier = Modifier.width(10.dp))
                    }
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
            Text(text = "Tap To See See Scheduled Shows For The Day", color = Color.Gray)
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))
        }


        item {
            LazyRow(state = scheduleState, modifier = Modifier.fillMaxWidth()) {
                itemsIndexed((0..6).toList()) { i, item ->
                    if (i != 0) Spacer(modifier = Modifier.width(20.dp))
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
                                showsViewModel.onScheduleChange(selectedDate.value)
                                Log.d("MAIN", "[SHOWS] ${scheduledShows}")
                                Log.d("MAIN", "[SELECTED] $selectedDateString")
                                when {
                                    i == 2 -> {
                                        scheduleState.animateScrollToItem(index = i - 2)
                                    }
                                    i > 1 || i == 1 -> {
                                        scheduleState.animateScrollToItem(index = i - 1)
                                    }
                                }
                            }
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    color = if (currentDayString == days[i]) Color.White.copy(
                                        0.2f
                                    ) else Color.parse("#ffafcc")
                                )
                                .width(90.dp)
                                .height(90.dp)
                                .border(
                                    1.dp,
                                    color = if (currentDayString == days[i]) Color.Gray else Color.White,
                                    CircleShape
                                ),
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
            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(
                        text = "Scheduled",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = "Listings",
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Gray,
                        style = MaterialTheme.typography.h6
                    )
                }
                Column(Modifier.padding(end = 10.dp),horizontalAlignment = Alignment.End) {
                    Text(text = currentDayString, color = Color.Gray)
                    Text(
                        text = selectedDateString,
                        color = Color.Gray,
                        style = MaterialTheme.typography.caption
                    )
                }
            }

        }
        item {
            ScheduledShows(list = if (scheduledShows.isEmpty()) empty else scheduledShows, onShowSetScheduleClick)

        }
    }
}

@Preview
@Composable
fun Preview() {
    val gradient = Brush.verticalGradient(listOf(Color.Black, Color.LightGray))
    ShowHome(rememberNavController(), gradient = Color.Black, showsViewModel = hiltViewModel<ShowViewModel>(), onShowClick = {} , onShowSetScheduleClick = { _, _ -> }) {

    }
}

fun <T> SnapshotStateList<T>.swapList(newList: List<T>) {
    clear()
    addAll(newList)
}