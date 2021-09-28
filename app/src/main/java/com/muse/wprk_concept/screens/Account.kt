package com.muse.wprk_concept.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.muse.wprk_concept.R

@Composable
fun Account(gradient: Brush) {
    //val paddingValues = PaddingValues(10.dp)

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        item {
            Row(
                modifier = Modifier.padding(10.dp)
            ){
                Text(text = "Businesses", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                MembershipCard()
                Spacer(modifier = Modifier.height(15.dp))
            }
        }

        item {
            Row(
                Modifier.padding(10.dp)
            ) {
                BusinessCard()
            }
        }

    }
}

@Composable
fun BusinessCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)

    ) {
        Row(Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Business Name", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Column() {
                    Text(text = "Business Description")
                    Text(text = "Business Description")
                    Text(text = "Business Description")
                }
            }

        }
    }
}


@Composable
fun MembershipCard() {
    val imageBitmap = ImageBitmap.imageResource(R.drawable.membership_card)
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .width(220.dp)
            .height(220.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {
        Image(
            painterResource(id = R.drawable.membership_card) ,
            contentDescription = "Membership Card",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(390.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(1.dp, color = Color.White, RoundedCornerShape(20.dp))
        )
    }
}



@Composable
@Preview
fun preview(){
    BusinessCard()
}

fun Color.Companion.parse(colorString: String): Color = Color(color = android.graphics.Color.parseColor(colorString))