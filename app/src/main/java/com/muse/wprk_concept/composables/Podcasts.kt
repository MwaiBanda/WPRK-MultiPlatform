package com.muse.wprk_concept.main

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun PodcastsHome(paddingValues: PaddingValues, gradient: Brush) {
    val scrollState = rememberScrollState()
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
                itemsIndexed((0..6).toList()) { i, item ->


                    if (i != 0) Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Gray)
                            .width(165.dp)
                            .height(180.dp)
                    )
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

    PodcastsHome(paddingValues = PaddingValues(), gradient = gradient)
}
