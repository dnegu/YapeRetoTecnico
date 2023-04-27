package com.dnegu.foodyape

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import com.dnegu.foodyape.data.model.response.RecipesResponse
import com.dnegu.foodyape.ui.detail.SmartView
import com.dnegu.foodyape.ui.splash.Splash
import com.dnegu.foodyape.ui.theme.FoodYapeTheme
import kotlinx.coroutines.withContext
import org.junit.Before

import org.junit.Test

import org.junit.Rule

class SmartViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            FoodYapeTheme{
                RecipesResponse.mock().articles?.firstOrNull()?.let {
                    SmartView(
                        it
                    )
                }
            }
        }
    }

    @Test
    fun myDisplayTest() {
        composeTestRule.onNodeWithContentDescription("ImageDescription").assertIsDisplayed()
        composeTestRule.onNodeWithText("Descripción").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ingredientes").assertIsDisplayed()
        composeTestRule.onNodeWithText("Preparación").assertIsDisplayed()
    }
}