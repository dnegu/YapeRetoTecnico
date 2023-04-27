package com.dnegu.foodyape.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dnegu.foodyape.R
import com.dnegu.foodyape.data.model.response.RecipesResponse
import com.dnegu.foodyape.domain.model.Recipes
import com.dnegu.foodyape.ui.base.AppComposable
import com.dnegu.foodyape.ui.composable.WebPageView
import com.dnegu.foodyape.ui.theme.FoodYapeTheme
import com.dnegu.foodyape.ui.theme.Purple40
import com.dnegu.foodyape.util.NavDestinations

@Composable
fun DetailScreen(
    recipesId: Int,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val recipes by viewModel.recipes.observeAsState(initial = null)
    val showSmartMode by viewModel.showSmartMode.observeAsState(initial = true)
    viewModel.getRecipesById(recipesId)
    AppComposable(
        viewModel = viewModel,
        content = {
            DetailScreen(
                navController,
                recipes,
                showSmartMode,
                toggleMode = { viewModel.toggleMode() })
        }
    )
}

@Composable
fun DetailScreen(
    navController: NavController,
    recipes: Recipes?,
    showSmartMode: Boolean,
    toggleMode: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipes?.name ?: "", maxLines = 1, overflow = TextOverflow.Ellipsis) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
                actions = {
                    TextButton(
                        onClick = toggleMode
                    ) {
                        Text(text = if (showSmartMode) "WEB" else "SMART", color = Color.White)
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(R.string.txt_see_ubication)) },
                shape = CircleShape,
                onClick = {
                    recipes?.let {
                        navController.navigate("${NavDestinations.LOCALIZATION_SCREEN}/${it.id}")
                    }
                },
                icon = { Icon(imageVector = Icons.Filled.Place, contentDescription = "icon") },
                contentColor = Purple40
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { pv ->
        recipes?.let {
            if (showSmartMode) {
                SmartView(recipes = it)
            } else {
                WebPageView(urlToRender = it.originalURL)
            }
        } ?: run {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        pv.toString()
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    FoodYapeTheme {
        DetailScreen(
            navController = rememberNavController(),
            recipes = RecipesResponse.mock().articles?.firstOrNull(),
            showSmartMode = true,
            toggleMode = {}
        )
    }
}
