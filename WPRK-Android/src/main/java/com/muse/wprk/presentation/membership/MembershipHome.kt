package com.muse.wprk_concept.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.outlined.TouchApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muse.wprk.R
import com.muse.wprk.core.exts.parse
import com.muse.wprk.main.model.Business
import com.muse.wprk.presentation.components.LiveButton


@Composable
fun MembershipHome(backgroundColor: Color, onLiveButtonClick: (String) -> Unit) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        item {

            Column {
                Spacer(modifier = Modifier.height(5.dp))
                Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {

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
                    }
                    LiveButton(Modifier.offset(y = 3.dp), onLiveButtonClick)
                }

                Spacer(modifier = Modifier.height(10.dp))
                Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)
                Spacer(modifier = Modifier.height(10.dp))

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
                    text = "Featured Deals",
                    color = Color.White,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "Discover Available Deals & Discounts", color = Color.Gray)
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
fun MembershipCard() {
    val context = LocalContext.current
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.parse("#BDBDBD"))
    ) {
        Image(
            painterResource(id = R.drawable.membership_card),
            contentDescription = "Membership Card",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(220.dp)
                .clip(RoundedCornerShape(20.dp))
        )
        Column(
            Modifier
                .fillMaxWidth(0.95f)
                .height(220.dp)
                .padding(bottom = 15.dp),
            verticalArrangement = Arrangement.Bottom ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = {
                val intent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse("https://securelb.imodules.com/s/1282/giving/index.aspx?sid=1282&gid=1&pgid=1418&cid=2828&dids=95")
                }
                context.startActivity(intent)
            }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append("Don't have a membership? ")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Color.parse("#ffafcc"),
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Register here")
                        }
                    }, color = Color.White)
                    Icon(imageVector = Icons.Outlined.TouchApp, contentDescription = "Membership URL Icon", tint = Color.White)
                }
            }
        }
    }
}
@Composable
fun BusinessCard(business: Business) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)

    ) {
        Row(
            Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
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
                        Row(verticalAlignment = Alignment.CenterVertically) {
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
                                text = business.category.uppercase(),
                                fontWeight = FontWeight.Light,
                                color = Color.White,
                                style = MaterialTheme.typography.caption
                            )
                        }
                    }
                    Row {
                        if (business.address.isNotEmpty())
                            IconButton(onClick = {
                                val intent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "${business.name},\n${business.address}")
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(intent, null)
                                context.startActivity(shareIntent)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Map,
                                    contentDescription = "${business.name} address",
                                    tint = Color.Gray
                                )
                            }
                        Spacer(modifier = Modifier.width(10.dp))
                        if (business.contact.isNotEmpty()) {
                            if (business.contact.first().isLetter()) {
                                IconButton(onClick = {
                                    val intent = Intent().apply {
                                        action = Intent.ACTION_SENDTO
                                        data = Uri.parse("mailto:${business.contact}")
                                    }
                                    context.startActivity(intent)
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Email,
                                        contentDescription = "${business.name} email",
                                        tint = Color.Gray
                                    )
                                }
                            } else {
                                IconButton(onClick = {
                                    val intent = Intent().apply {
                                        action = Intent.ACTION_DIAL
                                        data = Uri.parse("tel:${business.contact}")
                                    }
                                    context.startActivity(intent)
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Phone,
                                        contentDescription = "${business.name} phone number",
                                        tint = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = business.name,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = business.discount, color = Color.Gray)
                Spacer(modifier = Modifier.height(20.dp))
                Divider(color = Color.Gray.copy(0.3f), thickness = 1.dp)

            }

        }


    }
}

@Composable
fun BusinessCardContent() {
    val businessItems = listOf(
        Business(
            "drinks",
            "Nora's Sugar Shack",
            "$1 off bottles of wine and 6 packs, free gift while supply lasts over $10",
            "(407)447-5885",
            "636 Virginia Drive, Orlando, FL 32803"
        ),
        Business(
            "food",
            "Tako Cheena",
            "15% off",
            "(407)690-6270",
            "938 N Mills Ave, Orlando, FL 32803"
        ),
        Business(
            "food",
            "Pop's Pizzeria",
            "15% off",
            "(407)690-6270",
            "932 N Mills Ave, Orlando, FL 32803"
        ),
        Business(
            "clothing",
            "The Owl's Attic",
            "10% off all vintage sales",
            "(321)300-6957",
            "3106 Corrine Dr, Orlando, FL 32803"
        ),
        Business(
            "food",
            "Bolay",
            "10% off order",
            "(407)907-6577",
            "1971 Aloma Ave, Winter Park, FL 32792"
        ),
        Business(
            "media",
            "Annie Russell Theatre",
            "BOGO any Wednesday or Thursday show",
            "(407)389-1400",
            "1000 Holt Ave, Winter Park, FL 32789"
        ),
        Business(
            "food",
            "Antonella's Pizzeria",
            "20% off",
            "(407)636-5333",
            "360 W Fairbanks Ave, Winter Park, FL 32789"
        ),
        Business(
            "food",
            "Something Fishy",
            "Taco Wednesday for members and all Rollins students with id",
            "(407)951-8686",
            "249 W SR 436, Altamonte Springs FL 32714"
        ),
        Business(
            "service",
            "Window World Orlando",
            "$250 off of 4 or more",
            "(407)383-9693",
            "3882 Center Loop, Orlando, FL 32808"
        ),
        Business(
            "education",
            "The Princeton Review",
            "20% off select online courses *with coupon code",
            "",
            ""
        ),
        Business(
            "photography",
            "Kate Taramykin Studios",
            "10% off any session",
            "hello@katetaramykin.com",
            ""
        ),
        Business(
            "art",
            "Gabby Shepherd Artist",
            "WPRK 1 take 201% off select items",
            "gabbyshepherdartist@gmail.com",
            ""
        ),
        Business("media", "Aloma Cinema Grill", "1 free movie pass each month per member", "", ""),
        Business(
            "café",
            "Blackbird Comics & Coffeehouse",
            "10% off your purchase (cannot be combined with other discounts)",
            "(321)316-4296",
            "500 E Horatio Ave, Ste 3, Maitland, FL 32751"
        ),
        Business(
            "food",
            "Karina's Confectioneries",
            "$5 off any purchase of $75 or more and $10 off $150 or more for current WRK members (cannot be combined with other discounts)",
            "hello@karinasconfectioneries.com",
            "3201 Corrine Dr, Orlando, FL 32803"
        ),
        Business(
            "café",
            "KOS Coffee & Bodega",
            "Free Coffee (Drip, Espresso Based Drink, or Cold Brew)",
            "info@choosekos.com",
            "129 W Fairbanks Ave, Winter Park, FL 32789"
        ),
    )

    Spacer(modifier = Modifier.height(5.dp))
    businessItems.forEach { businessItem ->
        Spacer(modifier = Modifier.height(5.dp))
        BusinessCard(business = businessItem)
        Spacer(modifier = Modifier.height(5.dp))

    }

}


@Composable
@Preview
fun AccountsPreview() {
    val gradient = Brush.verticalGradient(listOf(Color.Black, Color.LightGray))
    MembershipHome(backgroundColor = Color.Black) { }
}

fun Color.Companion.parse(colorString: String): Color =
    Color(color = android.graphics.Color.parseColor(colorString))
