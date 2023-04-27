package com.dnegu.foodyape

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.dnegu.foodyape.ui.splash.Splash
import com.dnegu.foodyape.ui.theme.FoodYapeTheme

import org.junit.Test

import org.junit.Rule

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myLogoTest() {
        composeTestRule.setContent {
            FoodYapeTheme{
                Splash()
            }
        }
        composeTestRule.onNodeWithContentDescription("Logo Android").assertIsDisplayed()
    }
}