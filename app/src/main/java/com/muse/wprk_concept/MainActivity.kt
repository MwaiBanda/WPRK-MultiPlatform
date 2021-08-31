package com.muse.wprk_concept

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.muse.wprk_concept.composables.Account
import com.muse.wprk_concept.composables.Live
import com.muse.wprk_concept.ui.theme.WPRK_conceptTheme
import java.lang.StringBuilder
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import com.muse.wprk_concept.main.PodcastsHome
import com.muse.wprk_concept.main.Screen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight

import java.nio.file.Files.size

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            wprk_conceptApp { modifier, navController ->

                    NavHost(navController = navController, startDestination = Screen.Live.route) {
                        composable("podcasts") { PodcastsHome(paddingValues = PaddingValues()) }
                        composable("live") { Live(paddingValues = PaddingValues()) }
                        composable("account") { Account(paddingValues = PaddingValues()) }
                    }
            }
        }
    }
}

@Composable
fun wprk_conceptApp(content: @Composable (PaddingValues, NavHostController) -> Unit) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val bottomTabs = listOf(
        Screen.Live,
        Screen.Podcasts,
        Screen.Account
    )
    val menuNames = listOf(
        "Home", "Podcasts", "Account"
    )
    val navController = rememberNavController()
    WPRK_conceptTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                    title = {
                        Text(text = "WPRK")
                    },
                    navigationIcon = { IconButton(onClick = {
                        if (scaffoldState.drawerState.isClosed)
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }

                    }) {
                        Icon(Icons.Default.Menu, "")
                    } },
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                ) },
                drawerContent = {
                    DrawerHeader()
                },
                bottomBar = {
                    Column(modifier = Modifier.background(color = Color.Magenta)) {
                        Divider(color = Color.Black)
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row {
                                Spacer(modifier = Modifier.width(10.dp))

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(5.dp))
                                        .background(Color.Gray)
                                        .width(45.dp)
                                        .height(45.dp)
                                )
                                Spacer(modifier = Modifier.width(15.dp))

                                Column() {
                                    Text("Podcast Title", fontWeight = FontWeight.Bold)
                                    Text("This is a description", color = Color.Gray)
                                }
                            }
                            Row {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(Icons.Default.Bluetooth, "")
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(Icons.Default.PlayCircle, "")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        BottomNavigation(
                            backgroundColor = Color.Black,
                            contentColor = Color.White
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            bottomTabs.forEach { tab ->
                                BottomNavigationItem(
                                    icon = { Icon(tab.icon, "") },
                                    selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true,
                                    onClick = {
                                        navController.navigate(tab.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }

                                )
                            }

                        }
                    }
                },
                content = { modifier ->
                    // Vertical scroller is a composable that adds the ability to scroll through the
                    // child views
                    content(modifier, navController)
                }
            )

        }
    }
}

@Composable
fun DrawerHeader(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
       Box(
           modifier = Modifier
               .size(width = 150.dp, height = 150.dp)
               .clip(RoundedCornerShape(10.dp))
               .background(Color.Gray)
       )
        Spacer(modifier = Modifier.height(20.dp))
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    wprk_conceptApp{ modifier, _ ->
        PodcastsHome(paddingValues = modifier)
    }
}

