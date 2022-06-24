package com.muse.wprk.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.muse.wprk.main.model.MenuOption


@Composable
fun DrawerContent(gradient: Color){
    val menuItems = listOf(
        MenuOption("Spotify Playlist", Icons.Filled.PlaylistPlay),
        MenuOption("Support WPRK", Icons.Filled.AddModerator),
        MenuOption("Recent Spins", Icons.Filled.History),
        MenuOption("Get Involved", Icons.Filled.AttachMoney),
        MenuOption("Services", Icons.Filled.AddChart),
        MenuOption("About Us", Icons.Filled.Info),
        //MenuOption("Logout", Icons.Filled.Logout),


    )
    Column(
        modifier = Modifier
            .background(gradient)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        Spacer(modifier = Modifier.height(60.dp))
        Box(
            modifier = Modifier
                .size(width = 150.dp, height = 150.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.height(40.dp))
        menuItems.forEach { menuItem ->
            Column(
                modifier = Modifier
                    .clickable {  }
            ) {
                MenuItem(name = menuItem.name, icon = menuItem.icon)
            }
        }

    }
}
