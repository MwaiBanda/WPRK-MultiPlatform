package com.muse.wprk_concept.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muse.wprk.main.model.Show

@Composable
fun ScheduleUnit(
    title: String,
    category: String,
    time: String,
    isLast: () -> Boolean,
    onShowSetScheduleClick: () -> Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.Bottom ,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxWidth()
        ) {
            Column(Modifier.fillMaxWidth(0.75f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = title, color = Color.White, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)

                Text(text = category, color = Color.Gray, fontWeight = FontWeight.Normal)
            }
            if (title != "Nothing Scheduled")
                TextButton(
                    modifier = Modifier.offset(y = 10.dp),
                    onClick = { onShowSetScheduleClick() },
                ) {
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            imageVector = Icons.Default.Alarm,
                            contentDescription = "Access alarm",
                            tint = Color.LightGray
                        )
                        Text(
                            text = time,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 15.sp
                        )
                    }
                }

        }



    }
}