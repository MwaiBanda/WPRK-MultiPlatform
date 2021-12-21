package com.muse.wprk_concept.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
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
import androidx.compose.ui.unit.sp

@Composable
fun ScheduleUnit(
    title: String,
    category: String,
    time: String,
    isLast: (String) -> Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
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
                Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)

                Text(text = "$category", color = Color.Gray, fontWeight = FontWeight.Normal)
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

                Text(text = time, fontWeight = FontWeight.Bold, color = Color.White,  fontSize = 15.sp)
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        if (!isLast(title)) {
            Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
        }
        if (isLast(title)){
            Spacer(modifier = Modifier.height(50.dp))
        }

    }
}