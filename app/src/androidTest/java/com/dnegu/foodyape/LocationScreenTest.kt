package com.dnegu.foodyape

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.dnegu.foodyape.data.model.response.RecipesResponse
import com.dnegu.foodyape.ui.detail.SmartView
import com.dnegu.foodyape.ui.localization.Localization
import com.dnegu.foodyape.ui.localization.LocalizationScreen
import com.dnegu.foodyape.ui.splash.Splash
import com.dnegu.foodyape.ui.theme.FoodYapeTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberMarkerState
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.withContext
import org.junit.Before

import org.junit.Test

import org.junit.Rule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class LocationScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val startingZoom = 10f
    private val startingPosition = LatLng(1.23, 4.56)
    private lateinit var cameraPositionState: CameraPositionState

    private fun initFoodYapeTheme(){
        composeTestRule.setContent {
            FoodYapeTheme{
                RecipesResponse.mock().articles?.firstOrNull()?.let {
                    Localization(
                        rememberNavController(),
                        it
                    )
                }
            }
        }
    }

    private fun initMap(content: @Composable () -> Unit = {}) {
        check(hasValidApiKey) { "Maps API key not specified" }
        val countDownLatch = CountDownLatch(1)
        composeTestRule.setContent {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapLoaded = {
                    countDownLatch.countDown()
                }
            ) {
                content.invoke()
            }
        }
        val mapLoaded = countDownLatch.await(30, TimeUnit.SECONDS)
        assertTrue("Map loaded", mapLoaded)
    }



    @Before
    fun setup() {
        cameraPositionState = CameraPositionState(
            position = CameraPosition.fromLatLngZoom(
                startingPosition,
                startingZoom
            )
        )
    }

    @Test
    fun myDisplayTest() {
        initFoodYapeTheme()
        composeTestRule.onNodeWithContentDescription("Back").assertIsDisplayed()
        composeTestRule.onRoot().assertExists()
    }

    @Test
    fun myStartingCameraPosition() {
        initMap()
        startingPosition.assertEquals(cameraPositionState.position.target)
    }

    @Test(expected = IllegalStateException::class)
    fun myMarkerStateCannotBeReused() {
        initMap {
            val markerState = rememberMarkerState()
            Marker(
                state = markerState
            )
            Marker(
                state = markerState
            )
        }
    }
}