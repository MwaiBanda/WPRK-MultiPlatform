package com.muse.wprk_concept.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import com.muse.wprk_concept.composables.swapList
import com.muse.wprk_concept.data.Transistor.Podcast

@Composable
fun PodcastHome(navController: NavHostController, gradient: Brush, podcastViewModel: PodcastViewModel) {
    var podcasts = remember { mutableStateListOf<Podcast>() }
    val scrollState = rememberScrollState()
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
            Text(text = "What's On This Week?", fontWeight = FontWeight.ExtraBold, fontSize = 32.sp, color = Color.White)
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            LazyRow(state = LazyListState(), modifier = Modifier.fillMaxWidth()) {
                itemsIndexed((0..6).toList()) { i, item ->
                    if (i != 0) Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.Gray)
                            .width(165.dp)
                            .height(180.dp)
                    )
                    if (i == 6) Spacer(modifier = Modifier.width(10.dp))
                }


            }
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
