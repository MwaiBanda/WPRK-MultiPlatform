package com.muse.wprk_concept

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.muse.wprk.main.model.Podcast
import com.muse.wprk.main.usecase.GetEpisodesUseCase
import com.muse.wprk.main.usecase.GetPodcastsUseCase
import com.muse.wprk.presentation.podcasts.PodcastViewModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the WPRK-Android under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.muse.wprk_concept", appContext.packageName)

    }

    @Test
    fun testPodcasts() {

    }
}