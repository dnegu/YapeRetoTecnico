package com.dnegu.foodyape.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dnegu.foodyape.R
import com.dnegu.foodyape.data.model.response.RecipesResponse
import com.dnegu.foodyape.domain.model.Recipes
import com.dnegu.foodyape.ui.base.AppComposable
import com.dnegu.foodyape.ui.theme.FoodYapeTheme
import com.dnegu.foodyape.util.NavDestinations

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: ListViewModel = hiltViewModel()
) {
    val recipesList by viewModel.recipesList.observeAsState(initial = emptyList())
    viewModel.getRecipesList()
    AppComposable(
        viewModel = viewModel,
        content = { ListScreen(navController, recipesList,viewModel) }
    )
}

@Composable
fun ListScreen(
    navController: NavController,
    recipesList: List<Recipes>,
    viewModel: ListViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Recetas") },
            )
        }
    )
    {pv ->
        pv.toString()
        Column {
            Row {
                var text by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text(stringResource(R.string.txt_input_text_parameter)) },
                    modifier = Modifier.padding(24.dp,5.dp)
                )
                Button(
                    onClick = {
                        viewModel.searchWithParam(text)
                    },
                    modifier = Modifier
                        .padding(8.dp, 10.dp)
                        .align(Alignment.CenterVertically)) {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "SearchRecipe",
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("")
                }
            }
            LazyColumn {
                items(recipesList) { recipes ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .shadow(10.dp)
                            .clickable {
                                navController.navigate("${NavDestinations.DETAIL_SCREEN}/${recipes.id}")
                            }.testTag("CardClick"),
                    ) {
                        Row {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(recipes.imageURL)
                                    .crossfade(true)
                                    .build(),
                                placeholder = painterResource(R.drawable.placeholder),
                                error = painterResource(R.drawable.placeholder),
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(150.dp, 150.dp)
                                    .padding(16.dp),
                                contentDescription = "ImageRecipeCard"
                            )
                            Column {
                                Text(
                                    text = recipes.name ?: "",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = "Puntuación: ${recipes.rating}" ?: "",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 2,
                                    modifier = Modifier.padding(10.dp)
                                )
                                Text(
                                    text = recipes.origin.country ?: "",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 2,
                                    modifier = Modifier.padding(10.dp)
                                )
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
fun ListPreview() {
    FoodYapeTheme {
        ListScreen(
            navController = rememberNavController(),
            recipesList = RecipesResponse.mock().articles ?: emptyList(),
            viewModel = hiltViewModel()
        )
    }
}