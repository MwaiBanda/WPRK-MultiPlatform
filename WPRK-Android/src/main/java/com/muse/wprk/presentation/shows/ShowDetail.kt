package com.muse.wprk.presentation.shows

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.muse.wprk.core.utilities.ShowTime
import com.muse.wprk.main.model.Show
import com.muse.wprk.presentation.components.ExpandableText

@Composable
fun ShowDetail(
    show: Show,
    gradient: Color,
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(Modifier.fillMaxHeight(), state = lazyListState) {
        item {
            Box(modifier = Modifier.height(350.dp)) {
                Image(painter = rememberImagePainter(
                    data = show.image,
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
                            Text(text = if (show.category == "unset") "WPRK" else show.category ?: "WPRK", color = Color.Black)
                        }
                    }
                }
            }
        }
        item {
            Column(
                modifier = Modifier
                    .background(gradient)
                    .padding(horizontal = 10.dp)

            ) {
                Text(
                    text = show.title,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                ExpandableText(
                    text = show.description
                        .replace("<p>", "")
                        .replace("<p", "")
                        .replace("style=", "")
                        .replace("</strong", "")
                        .replace("\"margin-", "")
                        .replace("left:", "")
                        .replace("20px;", "")
                        .replace("</p>", "")
                        .replace("<a href=", "\n")
                        .replace("</a>", "")
                        .replace("&nbsp;", "")
                        .replace("<br>", "")
                        .replace("&amp;", "")
                        .replace("<em>", "")
                        .replace("</em>", "")
                        .replace("<strong>", "")
                        .replace("<span", "")
                        .replace("class=", "")
                        .replace("\"  \"", "")
                        .replace(">", "")
                        .replace("r-1qd0xha", "")
                        .replace("r-ad9z0x", "")
                        .replace("r-bcqeeo", "")
                        .replace("r-qvutc0", "")
                        .replace("css-901oao", "")
                        .replace("css-16my406", "")
                        .replace("</span", "")
                        .replace("</strong>", ""),
                    minimizedMaxLines = 4
                )
                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Schedule",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                ExpandableText(text = "Below Is The Scheduled Time", minimizedMaxLines = 4)
                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
            }
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = null,
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = show.displayDate(), color = Color.White)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    tint = Color.Gray
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "${show.getTime(ShowTime.START)} - ${show.getTime(ShowTime.END)}",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}