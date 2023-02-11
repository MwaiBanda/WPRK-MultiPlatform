package com.muse.wprk.presentation.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.muse.wprk.core.exts.parse
import com.mwaibanda.wprksdk.main.model.Business

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
