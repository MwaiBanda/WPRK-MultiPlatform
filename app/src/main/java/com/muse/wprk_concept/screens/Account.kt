package com.muse.wprk_concept.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import com.muse.wprk_concept.MenuItem
import com.muse.wprk_concept.R
import com.muse.wprk_concept.data.Business
import com.muse.wprk_concept.data.MenuOption
import com.muse.wprk_concept.parse

@Composable
fun Account(gradient: Brush) {
    //val paddingValues = PaddingValues(10.dp)
    val deals: String = "Deals"
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ){
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Businesses",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            MembershipCard()
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Divider(color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = deals, color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = Color.White)

        }

        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(gradient)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)

                ) {
                    BusinessCardContent()
                }
            }

        }
    }
}
@Composable
fun BusinessCard(name: String, description: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(129.dp)
            .clip(RoundedCornerShape(topEndPercent = 25))
            .background(color = Color.LightGray)
            .border(1.dp, color = Color.White, shape = RoundedCornerShape(topEndPercent = 25))
            .padding(10.dp)

    ) {
        Row(Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = name, color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline)
                Spacer(modifier = Modifier.height(5.dp))
                Column() {
                    Text(text = description,color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
            .height(140.dp)
            .width(210.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.parse("#BDBDBD"))
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
fun BusinessCardContent() {
    val businessItems = listOf(
        Business("Nora's Sugar Shack", "$1 off bottles of wine and 6 packs, free gift while supply lasts over $10"),
        Business("Tako Cheena", "15% off"),
        Business("Pop's Pizzeria", "15% off"),
        Business("The Owl's Attic", "10% off all vintage sales"),
        Business("Bolay", "10% off order"),
        Business("Annie Russell Theatre", "BOGO any Wednesday or Thursday show"),
        Business("Antonella's Pizzeria", "20% off"),
        Business("Something Fishy", "Taco Wednesday for members and all Rollins students with id"),
        Business("Window World Orlando", "$250 off of 4 or more"),
        Business("The Princeton Review", "20% off select online courses *with coupon code"),
        Business("Kate Taramykin Studios", "10% off any session"),
        Business("Gabby Shepherd Artist", "WPRK 1 take 201% off select items"),
        Business("Aloma Cinema Grill", "1 free movie pass each month per member"),
        Business("BAMF Comics & Coffee", "10% off your purchase (cannot be combined with other discounts)"),
        )

    Spacer(modifier = Modifier.height(20.dp))
    businessItems.forEach { businessItem ->
        Spacer(modifier = Modifier.height(15.dp))

                BusinessCard(name = businessItem.name, description = businessItem.discount)

        Spacer(modifier = Modifier.height(15.dp))

    }

}


fun Color.Companion.parse(colorString: String): Color = Color(color = android.graphics.Color.parseColor(colorString))
