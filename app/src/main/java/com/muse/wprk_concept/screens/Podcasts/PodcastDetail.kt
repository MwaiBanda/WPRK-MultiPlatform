package com.muse.wprk_concept.screens.Podcasts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.muse.wprk_concept.data.Transistor.Episode
import com.muse.wprk_concept.screens.Components.EpisodeRow
import com.muse.wprk_concept.screens.swapList

@Composable
fun PodcastDetail(
    navController: NavHostController,
    thumbnailURL: String?,
    showID: String?,
    title: String?,
    description: String?,
    gradient: Brush,
    podcastViewModel: PodcastViewModel,
    onEpisodeClick: (String) -> Unit
){
    val episodes = remember { mutableStateListOf<Episode>() }
    podcastViewModel.episode.observe(LocalLifecycleOwner.current){ newEpisodes ->
        episodes.swapList(newEpisodes)
    }
    LaunchedEffect(key1 = true) {
        podcastViewModel.getEpisodes(showID ?: "")
    }

    LazyColumn(Modifier.fillMaxHeight()) {
        item {
            Box(modifier = Modifier.height(350.dp)) {
                Image(painter = rememberImagePainter(
                    data = thumbnailURL ?: "",
                    onExecute = { _, _ -> true },
                    builder = {
                        crossfade(true)
                    }
                ),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(0.4f, Color.Transparent),
                                    Pair(1f, Color.Black)
                                )
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Box(
                            Modifier
                                .padding(start = 10.dp, bottom = 10.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .padding(horizontal = 15.dp, vertical = 5.dp)
                        ) {
                            Text(text = "Talk", color = Color.Black)
                        }
                    }
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .background(gradient)
                    .padding(start = 10.dp)
                    .fillParentMaxSize()

            ) {
                Text(
                    text = title ?: "",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(text = description ?: "")
                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn {
                    items(episodes) { episode ->
                        if (episodes.first() == episode) {
                            Divider(modifier = Modifier.padding(end = 10.dp))
                        }
                        EpisodeRow(onEpisodeClick = { onEpisodeClick(it)}, episode = episode)
                        if (episodes.last() != episode) {
                            Divider(modifier = Modifier.padding(end = 10.dp))
                        } else {
                            Spacer(modifier = Modifier.height(120.dp))
                        }
                    }
                }
                }

            }


    }
}

