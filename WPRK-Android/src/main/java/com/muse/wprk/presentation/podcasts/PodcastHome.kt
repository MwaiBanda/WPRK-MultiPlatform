package com.muse.wprk.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.muse.wprk.main.model.Episode
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.presentation.components.EpisodeRow
import com.muse.wprk.presentation.components.LiveButton
import com.muse.wprk.presentation.podcasts.PodcastViewModel
import com.muse.wprk.presentation.parse
import com.muse.wprk.presentation.swapList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun PodcastHome(
    navController: NavHostController,
    backgroundColor: Color,
    podcastViewModel: PodcastViewModel,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onEpisodeClick: (String) -> Unit
) {
    var podcasts = remember { mutableStateListOf<Podcast>() }
    val lifecycle = LocalLifecycleOwner.current
    val scheduleState = rememberLazyListState()
    var currentShow by remember { mutableStateOf(0) }
    var previousTab by remember { mutableStateOf(0) }
    var isTabingForward by remember { mutableStateOf(false) }
    var episodes = remember { mutableStateListOf<Episode>() }

    val navigateToDetail: (Podcast, String) -> Unit = { podcast, imageURL ->
        navController.navigate("pDetail/${podcast.id}/$imageURL/${podcast.title}/${podcast.description}")
    }
    LaunchedEffect(key1 = Unit) {
        podcastViewModel.getPodcasts {
            podcastViewModel.getFeaturedEpisodes(podcasts[currentShow].id)
        }
    }
    podcastViewModel.podcasts.observe(lifecycle) { newPodcasts ->
        podcasts.swapList(newPodcasts)
    }
    podcastViewModel.episodes.observe(lifecycle) { newEpisodes ->
        episodes.swapList(newEpisodes)
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(start = 10.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(5.dp))
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Podcasts",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.h5,
                        color = Color.White
                    )
                    Text(text = "Tap Featured Podcasts", color = Color.Gray)
                }
                LiveButton(Modifier.offset(y = 3.dp), onEpisodeClick)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            LazyRow(state = LazyListState(), modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(podcasts) { i, podcast ->
                    var imageURL =
                        URLEncoder.encode(podcast.thumbnailURL, StandardCharsets.UTF_8.toString())
                    if (i != 0) Spacer(modifier = Modifier.width(8.dp))
                    Box(Modifier.clickable { navigateToDetail(podcast, imageURL) }) {
                        Image(
                            painter = rememberImagePainter(
                                data = podcast.thumbnailURL,
                                onExecute = { _, _ -> true },
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
            Text(
                text = "Featured Episodes",
                fontWeight = FontWeight.ExtraBold,
                style = MaterialTheme.typography.h5,
                color = Color.White
            )
            Text(text = "Discover Popular Episodes From Our Podcasts", color = Color.Gray)
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            LazyRow(state = scheduleState, modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(podcasts) { i, podcast ->

                    if (i != 0) Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                            currentShow = podcasts.indexOf(podcast)
                            Log.d("MAIN", "[INDEX] $currentShow")
                            podcastViewModel.getFeaturedEpisodes(podcast.id)
                            coroutineScope.launch {
                                if (previousTab < currentShow) {
                                    isTabingForward = true
                                    previousTab = currentShow
                                } else {
                                    isTabingForward = false
                                    previousTab = currentShow
                                }
                                if (isTabingForward) {
                                    when {
                                        i > 1 || i == 1 -> {
                                            scheduleState.animateScrollToItem(index = i)
                                        }
                                    }
                                } else {
                                    when {
                                        i > 1 || i == 1 -> {
                                            scheduleState.animateScrollToItem(index = i - 1)
                                        }
                                    }
                                }
                            }
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(
                                        25f
                                    )
                                )
                                .background(
                                    color = if (currentShow == i) Color.Transparent else Color.parse(
                                        "#ffafcc"
                                    )
                                )
                                .height(65.dp)
                                .border(
                                    1.dp,
                                    color = if (currentShow == i) Color.Black else Color.White,
                                    RoundedCornerShape(25f)
                                )
                                .padding(horizontal = 15.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = podcast.title,
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    if (i == 6) Spacer(modifier = Modifier.width(10.dp))
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        itemsIndexed(episodes) { i, item ->
            EpisodeRow(episode = item) { onEpisodeClick(it) }
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
            if (item.id == episodes.last().id) {
                Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigateToDetail(
                                podcasts[currentShow],
                                getURL(podcasts, currentShow)
                            )
                        },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${if (podcasts.isEmpty()) 0 else podcasts[currentShow].episodesAvailable} Episodes Available",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "See More",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(end = 15.dp)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)

            }
        }

    }
}

fun getURL(list: List<Podcast>, index: Int): String {
    return URLEncoder.encode(list[index].thumbnailURL, StandardCharsets.UTF_8.toString())
}


@Composable
@Preview
fun PodcastsHomePreview() {

    val navController = rememberNavController()
    PodcastHome(
        navController = navController,
        backgroundColor = Color.Black,
        podcastViewModel = hiltViewModel<PodcastViewModel>()
    ) { }
}
