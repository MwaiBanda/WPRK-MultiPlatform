package com.muse.wprk.presentation.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.TouchApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.muse.wprk.R
import com.muse.wprk.core.exts.parse

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
