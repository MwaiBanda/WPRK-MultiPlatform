package com.muse.wprk.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.muse.wprk.R
import kotlinx.coroutines.delay

@Composable
fun ConnectivityWrapper(
    isConnected: Boolean,
    navController: NavHostController,
    paddingValues: PaddingValues,
    content: @Composable (NavHostController, PaddingValues) -> Unit
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.no_connection)
    )
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 1f,
        restartOnPlay = false

    )
    var isVisible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit){
        delay(1000)
        if (!isConnected)
            isVisible = true
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isVisible) {
                LottieAnimation(
                    composition = composition,
                    progress = progress,
                    modifier = Modifier.size(250.dp)
                )
                Text("No Internet Access", color = Color.Blue, fontWeight = FontWeight.Bold)
                Text(
                    "Make sure WiFi or Cellular data is \nturned on then try again.",
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
        }

        if (isConnected) {
            content(navController, paddingValues)
        }

    }
}