package com.muse.wprk.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MenuItem(name: String, icon: ImageVector){
    Spacer(modifier = Modifier.height(20.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .fillMaxWidth(0.55f)
            .alpha(0.35f)
            .background(Color.Black)
            .height(55.dp)
    ) {
        Row {
            Icon(
                imageVector = icon,
                contentDescription = "Menu Icon",
                tint = Color.White,
                modifier = Modifier.padding(start = 10.dp)

            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = name, fontWeight = FontWeight.ExtraBold, color = Color.White)
        }
    }
}