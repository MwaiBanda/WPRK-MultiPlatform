package com.muse.wprk_concept

import android.content.Context
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.muse.wprk_concept.core.NavigationRoutes
import com.muse.wprk_concept.core.exts.parse
import com.muse.wprk_concept.ui.theme.WPRK_conceptTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WPRKEntry(content: @Composable (NavHostController, PaddingValues, SimpleExoPlayer, ProvidableCompositionLocal<Context>, Color) -> Unit) {
    val gradient = Color.Black
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setSystemBarsColor(color = Color.Black,
            darkIcons = useDarkIcons)

    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val bottomTabs = listOf(
        NavigationRoutes.Live,
        NavigationRoutes.Podcasts,
        NavigationRoutes.Account
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
    var currentTitle by remember { mutableStateOf("") }
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
                        navigationIcon = {
                            Image(painter = rememberImagePainter(
                                data = "https://firebasestorage.googleapis.com/v0/b/wprk-c6825.appspot.com/o/IMG_4018-removebg-preview%403x.png?alt=media&token=2c203484-1186-4b8e-81c7-5a3186d7640d",
                                onExecute = { _, _ -> true },
                                builder = {
                                    crossfade(true)
                                }
                            ),
                                modifier = Modifier.size(50.dp),
                                contentScale = ContentScale.Crop,
                                contentDescription = null
                            )

                        },
                        backgroundColor = Color.Black,
                        contentColor = Color.White
                    ) },

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
                                .height(65.dp) // ADD Clickable Navigate to Detail
                                .clip(RoundedCornerShape(10.dp))
                                .background(color = Color.parse("#ffafcc"))
                                .border(1.dp, color = Color.White, RoundedCornerShape(10.dp)),

                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically

                        ) {
                            Row {
                                Spacer(modifier = Modifier.width(10.dp))

                                Box(
                                    modifier = Modifier
                                        .size(45.dp)
                                ) {
                                    Image(painter = rememberImagePainter(
                                        data = "https://firebasestorage.googleapis.com/v0/b/wprk-c6825.appspot.com/o/IMG_4018-removebg-preview%403x.png?alt=media&token=2c203484-1186-4b8e-81c7-5a3186d7640d",
                                        onExecute = { _, _ -> true },
                                        builder = {
                                            crossfade(true)
                                        }
                                    ),
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop,
                                        contentDescription = null
                                    )
                                }
                                Spacer(modifier = Modifier.width(15.dp))

                                Column() {
                                    Row(horizontalArrangement = Arrangement.Center) {
                                        Text(
                                            buildAnnotatedString {
                                                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                                                    append("WPRK 91.5")
                                                }
                                                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, fontSize = 13.sp)) {
                                                    append("FM")
                                                } },
                                            fontWeight = FontWeight.ExtraBold,
                                            color = Color.White
                                        )

                                    }
                                    AnimatedContent(
                                        targetState = count,
                                        transitionSpec = {
                                            if (targetState > initialState) {
                                                 slideInHorizontally(animationSpec = tween(
                                                     durationMillis = 9000
                                                 )){ width -> (width + (width * 0.3)).toInt() } + fadeIn(
                                                    animationSpec = tween(
                                                        durationMillis = 9000,
                                                    )) with slideOutHorizontally(
                                                    animationSpec = tween(
                                                        durationMillis = 9000,
                                                    )
                                                ) { width -> -(width + (width.toDouble() * 0.3).toInt()) } + fadeOut( animationSpec = tween(
                                                     durationMillis = 9000,
                                                 ))
                                            } else {
                                                slideInHorizontally(animationSpec = tween(
                                                    durationMillis = 9000,
                                                )){ width -> -(width +  (width.toDouble()  * 0.3).toInt()) } +  fadeIn(
                                                    animationSpec = tween(
                                                        durationMillis = 9000,
                                                    )
                                                )  with slideOutHorizontally(
                                                    animationSpec = tween(
                                                        durationMillis = 9000,
                                                    )
                                                ){ width -> (width + (width.toDouble()  * 0.3).toInt())  } + fadeOut()
                                            }
                                                .using(
                                                    SizeTransform(clip = true)
                                                )
                                        },
                                        modifier = Modifier.fillMaxWidth(0.8f)
                                    ) {
                                        Text(
                                            if (currentTitle == "") "Tune In..." else currentTitle,
                                            color = Color.White,
                                            maxLines = 1,
                                        )
                                    }
                                }
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
                        BottomNavigation(
                            backgroundColor = Color.Black,
                            contentColor = Color.White
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination

                            bottomTabs.forEach { tab ->
                                BottomNavigationItem(
                                    icon = { Icon(tab.icon ?: Icons.Default.PersonAdd, "") },
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
                    content(navController, it, player, context, gradient)
                }
            )
        }
    }
}