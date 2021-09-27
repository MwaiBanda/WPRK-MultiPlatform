package com.muse.wprk_concept.composables

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
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
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import com.muse.wprk_concept.R

@Composable
fun Account(gradient: Brush) {
    val buttonName = listOf("Account", "Card", "Deals")
    var selected by remember { mutableStateOf(0) }

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(start = 10.dp)
    ) {
        item {
            Text(text = "Account", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
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
        }

        item {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                buttonName.forEachIndexed { index, button ->
                    OutlinedButton(
                        border = BorderStroke(
                            2.dp,
                            color = if (selected == index) Color.Gray else Color.White
                        ),
                        shape = RoundedCornerShape(50),
                        onClick = { selected = index },
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))

                            .size(width = 100.dp, height = 43.dp)
                        ,
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = if (selected == index) Color.parse("#ffafcc") else Color.Transparent
                        ),
                    ) {
                        Text(
                            button,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }


            }
        }
        item {
            Spacer(modifier = Modifier.height(15.dp))
            Column {
                when (selected) {
                    0 -> AccountDetail()
                    1 -> CardDetail()
                    2 -> DealsDetail()
                }
            }
        }
    }
}

@Composable
fun MyButton(
    button_name: String,
    modifier: Modifier
) {
    Button(
        onClick = {},
        modifier = modifier.size(width = 100.dp, height = 43.dp),
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
fun TextFieldSample() {
    val labels by remember { mutableStateOf(listOf<String>(
        "name", "Email", "R-Number", "Phone Number", "Address", "State", "Zip Code"
    ))}
    var details by remember { mutableStateOf(listOf<String>(
        "Jane Doe", "jane_doe@gmail.com", "R0912392", "(405) 486-654", "apex avenue, west finley", "FL", "54665"
    ))}

    for (i in 0..details.lastIndex) {

        OutlinedTextField(value = details[i],
            onValueChange = {details },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = labels[i], fontWeight = FontWeight.Bold, color = Color.White)
                    },
            keyboardOptions = KeyboardOptions(
                KeyboardCapitalization.None, keyboardType = KeyboardType.Text
            )
        )
        Spacer(modifier = Modifier.height(20.dp))

    }

}

@Composable
fun MembershipCard() {
    val imageBitmap = ImageBitmap.imageResource(R.drawable.membership_card)
    Spacer(modifier = Modifier.height(10.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
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
        )



    }
}


@Composable
fun AccountDetail() {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),

        ) {
                    TextFieldSample()
        }

}



@Composable
fun CardDetail() {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding()

    ) {
        MembershipCard()
    }

}

@Composable
fun DealsDetail() {
    Text("Deals", color = Color.Black)
}


@Composable
@Preview
fun preview(){
    val gradient = Brush.verticalGradient(listOf(Color.Black,  Color.LightGray))
    Account(gradient = gradient)
}

fun Color.Companion.parse(colorString: String): Color = Color(color = android.graphics.Color.parseColor(colorString))