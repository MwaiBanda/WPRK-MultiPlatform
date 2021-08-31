package com.muse.wprk_concept.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Account(paddingValues: PaddingValues) {
    val buttonName = listOf("Account", "Card", "Deals")
    Column(

        Modifier
            .fillMaxSize()
            .padding(paddingValues = PaddingValues())
            .padding(10.dp)
    ) {
        Text(text = "Account", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Gray)
                    .width(180.dp)
                    .height(180.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
                ) {
            for (i in buttonName) {
              MyButton(button_name = i)
            }
        }

    }
}

@Composable
fun MyButton(
    button_name: String
) {
    Button(
        onClick = {},
        modifier = Modifier.size(width = 100.dp, height = 43.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor =
        Color.Gray),
        border = BorderStroke(
            1.dp,
            color = Color.Gray
        )
    ) {
        Text(
            text = button_name,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
@Preview
fun preview(){
    Account(paddingValues = PaddingValues())
}