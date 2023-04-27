package com.dnegu.foodyape

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.dnegu.foodyape.InitialSetup.getDetailViewModel
import com.dnegu.foodyape.data.model.response.RecipesResponse
import com.dnegu.foodyape.ui.detail.DetailScreen
import com.dnegu.foodyape.ui.list.ListScreen
import com.dnegu.foodyape.ui.splash.Splash
import com.dnegu.foodyape.ui.theme.FoodYapeTheme
import org.junit.Before

import org.junit.Test

import org.junit.Rule

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            FoodYapeTheme{
                RecipesResponse.mock().articles?.firstOrNull()?.id?.let {
                    DetailScreen(
                        recipesId = it,
                        navController = rememberNavController(),
                        viewModel = getDetailViewModel()
                    )
                }
            }
        }
    }

    @Test
    fun myDisplayTest() {
        composeTestRule.onNodeWithContentDescription("Back").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ver ubicación").assertIsDisplayed()
    }

    @Test
    fun myClickedTest() {
        composeTestRule.onNodeWithText("Ver ubicación").performClick()
    }

    @Test
    fun myImageTest() {
        composeTestRule.onNodeWithText("WEB").assertExists()
    }
}