package com.muse.wprk_concept.main

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.muse.wprk_concept.composables.Podcasts.PodcastViewModel
import com.muse.wprk_concept.composables.ScheduleUnit
import com.muse.wprk_concept.composables.swapList
import com.muse.wprk_concept.data.Show
import com.muse.wprk_concept.data.ShowTime
import com.muse.wprk_concept.data.Transistor.Podcast
import com.muse.wprk_concept.data.getFormattedDate
import com.muse.wprk_concept.data.getTime
import com.muse.wprk_concept.parse

@Composable
fun PodcastHome(navController: NavHostController, gradient: Brush, podcastViewModel: PodcastViewModel) {
    var days = remember { mutableStateListOf (
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    )}
    var abrev_days = remember { mutableStateListOf (
        "Sun", "Mon", "Tue", "Wed", "Th", "Fri", "Sat"
            ) }
    var selectedDate = remember { mutableStateOf(podcastViewModel.currentDay()) }

    var podcasts = remember { mutableStateListOf<Podcast>() }
    val scrollState = rememberScrollState()
    val lifecycle = LocalLifecycleOwner.current
    val showState = rememberLazyListState()
    val scheduleState = rememberLazyListState()
    val mainColumnState = rememberLazyListState()
    val columnState = rememberLazyListState()
    var shows = remember { mutableStateListOf<Show>()}
    var scheduledShows = remember { mutableStateListOf<Show>()}
    var currentDay by remember { mutableStateOf(0) }

    podcastViewModel.podcasts.observe(LocalLifecycleOwner.current) { newPodcasts ->
        podcasts.swapList(newPodcasts)
    }
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(start = 10.dp)
    ) {
        item {
            Text(text = "Today's Picks", fontWeight = FontWeight.ExtraBold, fontSize = 40.sp, color = Color.White)
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            LazyRow(state = LazyListState(), modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(podcasts) { i, podcast ->
                    if (i != 0) Spacer(modifier = Modifier.width(8.dp))
                    Box(Modifier.clickable { navController.navigate("podcastDetail/${podcast.id}") }) {
                        Image(
                            painter = rememberImagePainter(
                                data = podcast.attributes.image_url,
                                onExecute = {_,_ -> true},
                                builder = {
                                    crossfade(true)
                                    transformations(RoundedCornersTransformation(10f))
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .width(175.dp)
                                .height(180.dp)
                        )
                    }
                    if (i == 6) Spacer(modifier = Modifier.width(10.dp))
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
                itemsIndexed(podcasts) { i, item ->

                    if (i != 0) Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            selectedDate = when (i) {
                                0 -> {
                                    mutableStateOf(podcastViewModel.currentDay())
                                }
                                else -> {
                                    mutableStateOf(podcastViewModel.getDayByOffset(i.toLong()))
                                }
                            }
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
                            Text(text = abrev_days[i], color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                        }
                        Text(text = days[i],  color = Color.White)
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
@Preview
fun PodcastsHomePreview() {
    val gradient = Brush.verticalGradient(listOf(Color.Black,  Color.LightGray))
    val navController = rememberNavController()
    PodcastHome(navController = navController,gradient = gradient, podcastViewModel = hiltViewModel<PodcastViewModel>())
}
