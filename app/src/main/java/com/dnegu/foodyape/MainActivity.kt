package com.dnegu.foodyape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dnegu.foodyape.ui.theme.FoodYapeTheme
import com.dnegu.foodyape.data.model.response.RecipesResponse
import com.dnegu.foodyape.ui.detail.DetailScreen
import com.dnegu.foodyape.ui.list.ListScreen
import com.dnegu.foodyape.ui.localization.LocalizationScreen
import com.dnegu.foodyape.ui.splash.SplashScreen
import com.dnegu.foodyape.util.NavDestinations
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodYapeTheme (darkTheme = true){
                androidx.compose.material.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material.MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavDestinations.SPLASH_SCREEN,
                    ) {
                        composable(NavDestinations.SPLASH_SCREEN) {
                            SplashScreen(navController)
                        }
                        composable(NavDestinations.LIST_SCREEN) {
                            ListScreen(navController)
                        }
                        composable("${NavDestinations.DETAIL_SCREEN}/{recipesId}") {
                            it.arguments?.getString("recipesId")?.toInt()?.let { recipesId ->
                                DetailScreen(recipesId, navController)
                            }
                        }
                        composable("${NavDestinations.LOCALIZATION_SCREEN}/{recipesId2}"){
                            it.arguments?.getString("recipesId2")?.toInt()?.let { recipesId ->
                                LocalizationScreen(recipesId,navController)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodYapeTheme {
        ListScreen(
            navController = rememberNavController(),
            recipesList = RecipesResponse.mock().articles ?: emptyList(),
            viewModel = hiltViewModel()
        )
    }
}