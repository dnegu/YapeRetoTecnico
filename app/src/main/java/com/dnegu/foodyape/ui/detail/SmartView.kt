package com.dnegu.foodyape.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dnegu.foodyape.R
import com.dnegu.foodyape.data.model.response.RecipesResponse
import com.dnegu.foodyape.domain.model.Recipes
import com.dnegu.foodyape.ui.theme.FoodYapeTheme
import com.dnegu.foodyape.ui.theme.Purple40

@Composable
fun SmartView(
    recipes: Recipes,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(color = Color.Black)
            .verticalScroll(rememberScrollState()),
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipes.imageURL)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder),
                contentDescription = "ImageDescription",
                contentScale = ContentScale.FillWidth
            )
            Column(Modifier.padding(8.dp,8.dp,8.dp,60.dp)) {
                Text(recipes.name ?: "", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = colors.primary)
                Text(stringResource(R.string.txt_description), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = colors.secondary)
                Text(recipes.description ?: "", fontSize = 16.sp, color = Color.Gray)
                Text(stringResource(R.string.txt_ingredients), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = colors.secondary)
                recipes.ingredients.forEachIndexed { index, ingredient ->
                    Text("${index+1}.- \t ${ingredient.quantity} \t ${ingredient.name}", fontSize = 16.sp, color = Color.Gray)
                }
                Text(stringResource(R.string.txt_preparation), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = colors.secondary)
                recipes.steps.forEachIndexed { index, step ->
                    Text("${index+1}.- \t $step", fontSize = 16.sp, color = Color.Gray)
                }
            }

        }
    }
}

@Preview(showBackground = true,showSystemUi = true)
@Composable
fun SmartPreview() {
    FoodYapeTheme {
        RecipesResponse.mock().articles?.firstOrNull()?.let {
            SmartView(
                recipes = it
            )
        }
    }
}

