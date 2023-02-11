package com.muse.wprk.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.TouchApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.muse.wprk.presentation.components.BusinessCard
import com.mwaibanda.wprksdk.main.model.Business
import com.muse.wprk.presentation.components.LiveButton
import com.muse.wprk.presentation.components.MembershipCard
import com.mwaibanda.wprksdk.util.businessItems


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MembershipHome(backgroundColor: Color, onLiveButtonClick: (String) -> Unit) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        item {

            Column {
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
        stickyHeader {
            Column(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
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

        items(businessItems) { businessItem ->
            Spacer(modifier = Modifier.height(5.dp))
            BusinessCard(business = businessItem)
            Spacer(modifier = Modifier.height(5.dp))

        }

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
