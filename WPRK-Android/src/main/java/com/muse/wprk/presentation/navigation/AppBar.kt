package com.muse.wprk.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.mwaibanda.wprksdk.util.Constants
import com.muse.wprk.core.utilities.ScreenConfigurations

@Composable
fun TopAppBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    TopAppBar(
        title = {
            if (!ScreenConfigurations.screensWithBackButton.contains(currentRoute?.take(7)))
                Row(
                    Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = Constants.LOGO_URL)
                            .apply(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                            }).build()
                    ),
                        modifier = Modifier
                            .size(50.dp)
                            .offset(x = (-35).dp),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                }
        },
        navigationIcon = {
            if (ScreenConfigurations.screensWithBackButton.contains(currentRoute?.take(7)))

            IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Row {
                        Icon(Icons.Filled.ArrowBackIosNew, "")
                        Text(text = "Back", color = Color.White)
                    }
                }

        },
        backgroundColor = Color.Black,
        contentColor = Color.White
    )
}