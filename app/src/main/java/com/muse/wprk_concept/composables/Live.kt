package com.muse.wprk_concept.composables

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Live(paddingValues: PaddingValues, gradient: Brush) {
    val days = listOf(
        "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    )
    val rowState = rememberLazyListState()
    val columnState = rememberLazyListState()

    LazyColumn(
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
                Spacer(modifier = Modifier.fillMaxWidth(0.6f))
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
                }
            }
            Text(text = "Currently Scheduled Today ", color = Color.White)
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
            Text(text = "Schedule", fontWeight = FontWeight.ExtraBold, fontSize = 40.sp,  color = Color.White)
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
                        Text(text = days[item],  color = Color.White)
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
                state = LazyListState(),
                modifier = Modifier.height(300.dp)
            ) {
                itemsIndexed((0..6).toList()) { i, item ->

                    ScheduleUnit(title = "The Mark Show", author = "Mark", time = "8:30pm")

                }
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun ScheduleUnit(title: String, author: String, time: String) {
    Box(
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
                Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold)
                Text(text = "by $author", color = Color.White, fontWeight = FontWeight.Normal)
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.size(20.dp),
                ) {
                    Icon(
                        Icons.Default.Alarm,
                        contentDescription = "Access alarm",
                        tint = Color.LightGray
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))

                Text(text = time, fontWeight = FontWeight.Normal, color = Color.White)
            }

        }
    }
}

@Preview
@Composable
fun Preview() {
    val gradient = Brush.verticalGradient(listOf(Color.Black,  Color.LightGray))

    Live(paddingValues = PaddingValues(), gradient = gradient)
}