package com.muse.wprk_concept.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ScheduleUnit(title: String, category: String, time: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
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
                Text(text = "$category", color = Color.White, fontWeight = FontWeight.Normal)
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