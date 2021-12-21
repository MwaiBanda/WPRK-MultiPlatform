package com.muse.wprk_concept.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muse.wprk_concept.R
import com.muse.wprk_concept.data.Business


@Composable
fun Account(gradient: Color) {
    val deals: String = "Featured Deals"
    val dicoverDeals: String = "Discover Available Deals & Discounts"
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ){
        item {
            Row(
                modifier = Modifier.padding(10.dp)
            ) {
                Column() {

                    Text(
                        text = "Membership",
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        text = "Become A Member Today",
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                MembershipCard()
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = deals,
                    color = Color.White,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Text(text = dicoverDeals, color = Color.Gray)
                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
            }
        }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    BusinessCardContent()

                }
            }

        }
    }




@Composable
fun BusinessCard(name: String, category: String, description: String, ) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

    ) {
        Row(Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .border(
                            BorderStroke(
                                1.dp,
                                color = Color.White
                            ), RoundedCornerShape(50.dp)
                        )
                        .padding(horizontal = 15.dp, vertical = 8.dp)
                ) {
                    Row(verticalAlignment  = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(color = Color.parse("#ffafcc"))
                                .width(10.dp)
                                .height(10.dp)
                                .border(1.dp, color = Color.White, CircleShape)
                        )
                        Spacer(Modifier.width(5.dp))
                    Text(
                        text = category.uppercase(),
                        fontWeight = FontWeight.Light,
                        color = Color.White,
                        style = MaterialTheme.typography.caption
                    )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = name, color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = description,color = Color.Gray)
                Spacer(modifier = Modifier.height(20.dp))
                Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)

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
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.parse("#BDBDBD"))
    ) {
        Image(
            painterResource(id = R.drawable.membership_card) ,
            contentDescription = "Membership Card",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(220.dp)
                .clip(RoundedCornerShape(20.dp))
        )
    }
}

@Composable
fun BusinessCardContent() {
    val businessItems = listOf(
        Business("drinks","Nora's Sugar Shack", "$1 off bottles of wine and 6 packs, free gift while supply lasts over $10"),
        Business("food","Tako Cheena", "15% off"),
        Business("food","Pop's Pizzeria", "15% off"),
        Business("clothing","The Owl's Attic", "10% off all vintage sales"),
        Business("food","Bolay", "10% off order"),
        Business("media","Annie Russell Theatre", "BOGO any Wednesday or Thursday show"),
        Business("food","Antonella's Pizzeria", "20% off"),
        Business("food","Something Fishy", "Taco Wednesday for members and all Rollins students with id"),
        Business("service","Window World Orlando", "$250 off of 4 or more"),
        Business("education","The Princeton Review", "20% off select online courses *with coupon code"),
        Business("photography","Kate Taramykin Studios", "10% off any session"),
        Business("art","Gabby Shepherd Artist", "WPRK 1 take 201% off select items"),
        Business("media","Aloma Cinema Grill", "1 free movie pass each month per member"),
        Business("café","BAMF Comics & Coffee", "10% off your purchase (cannot be combined with other discounts)"),
        )

    Spacer(modifier = Modifier.height(5.dp))
    businessItems.forEach { businessItem ->
        Spacer(modifier = Modifier.height(5.dp))
        BusinessCard(name = businessItem.name, category = businessItem.category,description = businessItem.discount)
        Spacer(modifier = Modifier.height(5.dp))

    }

}

@Composable
@Preview 
fun AccountsPreview() {
    val gradient = Brush.verticalGradient(listOf(Color.Black,  Color.LightGray))
    Account(gradient = Color.Black)
}

fun Color.Companion.parse(colorString: String): Color = Color(color = android.graphics.Color.parseColor(colorString))
