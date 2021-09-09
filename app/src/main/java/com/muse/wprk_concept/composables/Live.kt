package com.muse.wprk_concept.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessAlarm
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Live(paddingValues: PaddingValues,) {
    val days = listOf(
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    )
    val rowState = rememberLazyListState()
    val columnState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp),
    ) {
        item {
            Text(text = "Shows", fontWeight = FontWeight.ExtraBold, fontSize = 40.sp)
            Text(text = "Currently Scheduled Today ", fontWeight = FontWeight.Thin, color = Color.Gray)
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            LazyRow(state = rowState, modifier = Modifier.fillMaxWidth()) {
                itemsIndexed((0..6).toList()) { i, item ->


                    if (i != 0) Spacer(modifier = Modifier.width(10.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Gray)
                            .width(175.dp)
                            .height(180.dp)
                    )
                    if (i == 6) Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Schedule", fontWeight = FontWeight.ExtraBold, fontSize = 40.sp)
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            LazyRow(state = columnState, modifier = Modifier.fillMaxWidth()) {
                itemsIndexed((0..6).toList()) { i, item ->

                    if (i != 0) Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.Gray)
                                .width(90.dp)
                                .height(90.dp)
                        )
                        Text(text = days[item])
                    }

                    if (i == 6) Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
        item {

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Scheduled Shows", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = "Listings", fontWeight = FontWeight.Thin, color = Color.Gray)

        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                state = LazyListState(),
                modifier = Modifier.height(300.dp)
            ) {
                itemsIndexed((0..6).toList()) { i, item ->

                    ScheduleUnit(title = "The mark show", author = "Mark", time = "20:30")

                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun ScheduleUnit(title: String, author: String, time: String) {
    Row(
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
                Text(text = title)
                Text(text = "by $author", color = Color.Gray, fontWeight = FontWeight.Thin)
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.size(20.dp)
                    ) {
                        Icon(Icons.Default.Alarm, contentDescription = "Access alarm")
                    }


                Text(text = time)
            }

        }
    }
}

@Preview
@Composable
fun Preview() {
    Live(paddingValues = PaddingValues())
}