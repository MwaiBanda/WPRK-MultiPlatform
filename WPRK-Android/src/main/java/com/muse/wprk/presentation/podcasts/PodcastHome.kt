package com.muse.wprk.main

import android.util.Log
import androidx.compose.foundation.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.mwaibanda.wprksdk.main.model.Episode
import com.mwaibanda.wprksdk.main.model.Podcast
import com.muse.wprk.presentation.components.EpisodeRow
import com.muse.wprk.presentation.components.LiveButton
import com.muse.wprk.presentation.podcasts.PodcastViewModel
import com.muse.wprk.presentation.parse
import com.muse.wprk.presentation.swapList
import com.mwaibanda.wprksdk.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalFoundationApi::class)
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
    val mainColumnState = rememberLazyListState()
    val scheduleState = rememberLazyListState()
    var selectedTabIndex by remember { mutableStateOf(0) }
    var previousTabIndex by remember { mutableStateOf(0) }
    var isTabingForward by remember { mutableStateOf(false) }
    var episodes = remember { mutableStateListOf<Episode>() }

    val navigateToDetail: (Podcast, String) -> Unit = { podcast, imageURL ->
        navController.navigate("pDetail/${podcast.id}/$imageURL/${podcast.title}/${podcast.description}")
    }
    LaunchedEffect(key1 = Unit) {
        podcastViewModel.getPodcasts {
            if (podcastViewModel.podcasts.value.orEmpty().isNotEmpty()) {
                podcastViewModel.getFeaturedEpisodes(podcasts[selectedTabIndex].id)
            }
        }
    }
    podcastViewModel.podcasts.observe(lifecycle) { newPodcasts ->
        podcasts.swapList(newPodcasts)
    }
    podcastViewModel.episodes.observe(lifecycle) { newEpisodes ->
        episodes.swapList(newEpisodes)
    }

    LazyColumn(
        state = mainColumnState,
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(start = 10.dp)
    ) {
        item {
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
                LazyRow(state = rememberLazyListState(), modifier = Modifier.fillMaxWidth()) {
                    if (podcasts.isEmpty()) {
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
                        itemsIndexed(podcasts) { i, podcast ->
                            var imageURL =
                                URLEncoder.encode(
                                    podcast.thumbnailURL,
                                    StandardCharsets.UTF_8.toString()
                                )
                            if (i != 0) Spacer(modifier = Modifier.width(8.dp))
                            Box(Modifier.clickable { navigateToDetail(podcast, imageURL) }) {
                                Image(
                                    painter = rememberAsyncImagePainter(
                                        ImageRequest.Builder(LocalContext.current)
                                            .data(data = podcast.thumbnailURL)
                                            .apply(block = fun ImageRequest.Builder.() {
                                                crossfade(true)
                                                transformations(RoundedCornersTransformation(10f))
                                            }).build()
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
            }
item {
    Spacer(modifier = Modifier.height(10.dp))

}
        stickyHeader {

    Column(Modifier.background(Color.Black)) {


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

            LazyRow(state = scheduleState, modifier = Modifier.fillMaxWidth()) {
                if (podcasts.isEmpty()) {
                    itemsIndexed(MutableList(12){ return@MutableList 0 }) { i, _ ->
                        if (i != 0) Spacer(modifier = Modifier.width(10.dp))
                        Box(
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(
                                        25f
                                    )
                                )
                                .background(color = Color.Transparent)
                                .height(65.dp)
                                .border(
                                    1.dp,
                                    color = Color.Black,
                                    RoundedCornerShape(25f)
                                )
                                .padding(horizontal = 15.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                } else {
                itemsIndexed(podcasts.filter { it.title != Constants.ANNIVERSARY }) { currentTabIndex, podcast ->

                    if (currentTabIndex != 0) Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                        selectedTabIndex = currentTabIndex
                            Log.d("MAIN", "[INDEX] $currentTabIndex")
                            podcastViewModel.getFeaturedEpisodes(podcast.id)
                            coroutineScope.launch {
                                if (previousTabIndex < currentTabIndex) {
                                    isTabingForward = true
                                    previousTabIndex = currentTabIndex
                                } else {
                                    isTabingForward = false
                                    previousTabIndex = currentTabIndex
                                }
                                if (isTabingForward) {
                                    when {
                                        currentTabIndex >= 1 -> {
                                            scheduleState.animateScrollToItem(index = currentTabIndex)
                                        }
                                    }
                                } else {
                                    when {
                                        currentTabIndex >= 1 -> {
                                            scheduleState.animateScrollToItem(index = currentTabIndex - 1)
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
                                    color = if (currentTabIndex == selectedTabIndex) Color.Transparent else Color.parse(
                                        "#ffafcc"
                                    )
                                )
                                .height(65.dp)
                                .border(
                                    1.dp,
                                    color = if (currentTabIndex == selectedTabIndex) Color.Black else Color.White,
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

                    if (currentTabIndex == 6) Spacer(modifier = Modifier.width(10.dp))
                }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)

            Spacer(modifier = Modifier.height(10.dp))

    }
        }
            item {
                Column(Modifier.heightIn(min = 400.dp)) {
                    episodes.forEach { item ->
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
                                            podcasts[selectedTabIndex],
                                            getURL(podcasts, selectedTabIndex)
                                        )
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${if (podcasts.isEmpty()) 0 else podcasts[selectedTabIndex].episodesAvailable} Episodes Available",
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
