package com.muse.wprk.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muse.wprk.core.exts.parse

@Composable
fun Button(text: String) {
    Column(modifier = Modifier.padding(15.dp)) {
        androidx.compose.material.Button(
            onClick = { },
            Modifier.height(65.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp)),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.parse("#FEAFCC")
            )
        ) {
            Text(text = text, color = Color.White, fontSize = 20.sp)
        }
    }
}