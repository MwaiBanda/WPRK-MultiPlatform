package com.muse.wprk.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Podcasts
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mwaibanda.wprksdk.util.Constants

@Composable
fun LiveButton(modifier: Modifier, onSwitchToDefault: (String) -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(end = 10.dp)
            .border(
                BorderStroke(
                    1.dp,
                    color = Color.White
                ), RoundedCornerShape(10.dp)
            )
            .height(height = 55.dp)


    ) {
        Row(Modifier.padding(horizontal = 15.dp)
            .padding(vertical = 8.dp).clickable { onSwitchToDefault(Constants.DEFAULT_STREAM) }) {
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
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Filled.Podcasts,
                contentDescription = "",
                tint = Color.Red,
                modifier = Modifier
                    .size(18.dp, 18.dp)
                    .offset(y = 2.dp)
            )
        }
    }
}