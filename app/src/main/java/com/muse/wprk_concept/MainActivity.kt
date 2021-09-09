package com.muse.wprk_concept

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.text.font.FontWeight
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaItem.fromUri
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.muse.wprk_concept.composables.DetailScreen
import com.muse.wprk_concept.composables.PlayerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WPRKEntry { navController, player, context ->
                    NavHost(navController = navController, startDestination = Screen.Live.route) {
                        composable("podcasts") { PodcastsHome(paddingValues = PaddingValues()) }
                        composable("live") { Live(paddingValues = PaddingValues()) }
                        composable("account") { Account(paddingValues = PaddingValues()) }
                        composable("playerDetail") { DetailScreen(player = player) }
                        composable("playerView") { PlayerView(player = player, context = context) }
                    }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WPRKEntry(content: @Composable (NavHostController, SimpleExoPlayer, ProvidableCompositionLocal<Context>) -> Unit) {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setSystemBarsColor(
            color = Color.Black,
            darkIcons = useDarkIcons
        )
    }
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
    val context = LocalContext
    val current = context.current
    val player = remember {

        SimpleExoPlayer.Builder(current).build().apply {
            val dataSourceFactory = DefaultDataSourceFactory(
                current,
                Util.getUserAgent(current, current.packageName)
            )
            val media = MediaItem.Builder()
                .setUri("http://wprk.broadcasttool.stream:80/stream")
                .setLiveTargetOffsetMs(5000)
                .setLiveMaxPlaybackSpeed(1.02f)
                .build()

            val source = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(media)

            setMediaSource(source)
            setHandleAudioBecomingNoisy(true)
            prepare()
        }

    }

    var isPlaying by remember { mutableStateOf(false) }
    var currentTitle by remember { mutableStateOf("")}
    var count by remember { mutableStateOf(0) }

    WPRK_conceptTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                    title = {
                        Text(text = "WPRK", fontWeight = FontWeight.ExtraBold)
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
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier
                            .height(10.dp),
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.95f)
                                .height(65.dp)
                                .clickable { navController.navigate(Screen.DetailScreen.route) }
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = Color.parse("#ffafcc")),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

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
                                    Row(horizontalArrangement = Arrangement.Center) {
                                        Text(
                                            "WPRPK Live",
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color.White
                                        )
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Icon(
                                            imageVector = Icons.Filled.Podcasts,
                                            contentDescription = "",
                                            tint = Color.Red,
                                            modifier = Modifier
                                                .size(18.dp, 18.dp)
                                                .offset(y = 2.dp)
                                        )
                                    }
                                    AnimatedContent(
                                        targetState = count,
                                        transitionSpec = {
                                            if (targetState > initialState) {
                                                    slideInHorizontally({ width -> (width + (width.toDouble()  * 0.3).toInt()) }, animationSpec = tween(durationMillis = 9000, delayMillis = 4000)) + fadeIn(animationSpec = tween(delayMillis = 3000))  with
                                                            slideOutHorizontally({ width -> -(width +  (width.toDouble()  * 0.3).toInt())  }, animationSpec = tween(durationMillis = 9000, delayMillis = 4000))
                                            } else {
                                                slideInHorizontally({ width -> -(width +  (width.toDouble()  * 0.3).toInt()) }, animationSpec = tween(durationMillis = 9000, delayMillis = 4000)) + fadeIn(animationSpec = tween(delayMillis = 3000))  with
                                                        slideOutHorizontally({ width -> (width + (width.toDouble()  * 0.3).toInt())  }, animationSpec = tween(durationMillis = 9000, delayMillis = 4000))
                                            }.using(
                                                SizeTransform(clip = true)
                                            )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.7f)
                                    ) { 
                                        Text(
                                            if (currentTitle == "") "Tune In..." else currentTitle,
                                            color = Color.White,
                                            maxLines = 1,
                                        )
                                    }
                                }
                            }
                            Row(horizontalArrangement = Arrangement.SpaceAround) {
                                IconButton(onClick = { /*TODO*/ }, modifier = Modifier.offset(x = (-5).dp)) {
                                    Icon(Icons.Default.Bluetooth, "", tint = Color.White)
                                }
                                IconButton(onClick = {
                                    when(player.isPlaying) {
                                        true  ->    player.pause()
                                            .also {
                                                isPlaying = false
                                                count--
                                            }

                                        false ->    player.play()
                                            .also {
                                                isPlaying = true
                                                count++
                                            }
                                            .also{ currentTitle = player.mediaMetadata.title.toString()}
                                            .also { Log.d("MAIN", currentTitle )}
                                            .also { scope.launch {
                                                while(isPlaying) {
                                                    if (!isPlaying) break
                                                    delay(1000L)
                                                    currentTitle = player.mediaMetadata.title.toString()
                                                    delay(19000L)
                                                    count++
                                                }
                                              }
                                            }
                                }}, modifier = Modifier.offset(x = (-10).dp)){
                                    Icon(
                                        when(isPlaying) {
                                            true -> Icons.Default.PauseCircle
                                            false -> Icons.Default.PlayCircle
                                        },
                                        "",
                                        tint = Color.White,
                                        modifier = Modifier.size(44.dp, 44.dp)
                                    )
                                }

                            }

                        }
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
                content = {
                    // Vertical scroller is a composable that adds the ability to scroll through the
                    // child views
                    content(navController, player, context)
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

}

fun Color.Companion.parse(colorString: String): Color = Color(color = android.graphics.Color.parseColor(colorString))