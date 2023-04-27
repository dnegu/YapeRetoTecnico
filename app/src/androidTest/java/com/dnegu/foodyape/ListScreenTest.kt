package com.dnegu.foodyape

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.dnegu.foodyape.ui.list.ListScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            ListScreen(rememberNavController(), InitialSetup.getListViewModel())
        }
    }

    @Test
    fun myDisplayTest() {
        composeTestRule.onNodeWithText("Ingrese parametro de busqueda").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("SearchRecipe").assertIsDisplayed()
    }

    @Test
    fun myContentDescriptionClickedTest() {
        composeTestRule.onNodeWithContentDescription("SearchRecipe").performClick()
    }

    @Test
    fun myContentDescriptionAllTest() {
        composeTestRule.onAllNodesWithContentDescription("ImageRecipeCard", useUnmergedTree = false)
    }

    @Test
    fun myCountChildrenTest() {
        composeTestRule.onNodeWithText("Ingrese parametro de busqueda").onChildren().assertCountEquals(0)
    }

    @Test
    fun myAllCardClickedTest() {
        composeTestRule.onAllNodesWithTag("CardClick").assertAll(hasClickAction())
    }


}