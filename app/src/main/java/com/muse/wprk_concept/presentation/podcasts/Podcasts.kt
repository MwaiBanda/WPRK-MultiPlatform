package com.muse.wprk_concept.main

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Podcasts
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.muse.wprk_concept.main.model.Episode
import com.muse.wprk_concept.main.model.Podcast
import com.muse.wprk_concept.presentation.components.EpisodeRow
import com.muse.wprk_concept.presentation.parse
import com.muse.wprk_concept.presentation.podcasts.PodcastViewModel
import com.muse.wprk_concept.presentation.swapList
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun PodcastHome(
    navController: NavHostController,
    gradient: Color,
    podcastViewModel: PodcastViewModel,
    onSwitchToDefault: () -> Unit,
    onEpisodeClick: (String) -> Unit
) {
    var podcasts = remember { mutableStateListOf<Podcast>() }
    val lifecycle = LocalLifecycleOwner.current
    val scheduleState = rememberLazyListState()
    val columnState = rememberLazyListState()
    var currentShow by remember { mutableStateOf(0) }
    var episodes = remember { mutableStateListOf<Episode>()}

    val navigateToDetail: (Podcast, String) -> Unit = { podcast, imageURL ->
        navController.navigate("podcastDetail/${podcast.id}/$imageURL/${podcast.title}/${podcast.description}")
    }
    LaunchedEffect(key1 = Unit) {
        podcastViewModel.getPodcasts {
            podcastViewModel.getEpisodes(podcasts[currentShow].id)
        }
    }
    podcastViewModel.podcasts.observe(lifecycle) { newPodcasts ->
        podcasts.swapList(newPodcasts)
    }
    podcastViewModel.episodeDTO.observe(lifecycle) { newEpisodes ->
            episodes.swapList(newEpisodes)
    }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(start = 10.dp)
    ) {
        item {
            Row (Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Podcasts", fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.h5,
                    color = Color.White)
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
                            withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, fontSize = 13.sp)) {
                                append("FM")
                            } },
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
            Text(text = "Discover Featured Podcasts", color = Color.Gray)
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            LazyRow(state = LazyListState(), modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(podcasts) { i, podcast ->
                    var imageURL = URLEncoder.encode(podcast.thumbnailURL, StandardCharsets.UTF_8.toString())
                    if (i != 0) Spacer(modifier = Modifier.width(8.dp))
                    Box(Modifier.clickable { navigateToDetail(podcast, imageURL) }) {
                        Image(
                            painter = rememberImagePainter(
                                data = podcast.thumbnailURL,
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
            Text(text = "Featured Episodes", fontWeight = FontWeight.ExtraBold, style = MaterialTheme.typography.h5, color = Color.White)
            Text(text = "Discover Popular Episodes", color = Color.Gray)
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            LazyRow(state = scheduleState, modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(podcasts) { i, item ->

                    if (i != 0) Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable {
                        currentShow = podcasts.indexOf(item)
                            Log.d("MAIN", "[INDEX] $currentShow")
                            podcastViewModel.getEpisodes(item.id)
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
                                .padding(horizontal = 15.dp)
                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = item.title, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
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
            LazyColumn(
                state = columnState,
                modifier = Modifier.height(350.dp)
            ) {
                item {
                    episodes.forEachIndexed { i, item ->
                        if(i < 4) {
                            EpisodeRow(onEpisodeClick = { onEpisodeClick(it) }, episodeDTO = item)
                            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
                        }
                        if (i == 4 || item.id == episodes.last().id  && episodes.count()  < 5) {
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
                                Text(text = "${if (podcasts.isEmpty()) 0 else podcasts[currentShow].episodesAvailable} Episodes Available", fontWeight = FontWeight.Bold, color = Color.White)
                                Text(text = "See More", fontWeight = FontWeight.Bold,  color = Color.White, modifier = Modifier.padding(end = 15.dp))
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)

                        }
                    }
                }


            }

            Spacer(modifier = Modifier.height(80.dp))
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
    PodcastHome(navController = navController,gradient = Color.Black, podcastViewModel = hiltViewModel<PodcastViewModel>(), onSwitchToDefault =  {}){

    }
}
