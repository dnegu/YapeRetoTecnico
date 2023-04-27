package com.dnegu.foodyape.ui.localization

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dnegu.foodyape.data.model.response.RecipesResponse
import com.dnegu.foodyape.domain.model.Recipes
import com.dnegu.foodyape.ui.detail.DetailViewModel
import com.dnegu.foodyape.ui.theme.FoodYapeTheme
import com.dnegu.foodyape.util.Constants.ZOOM_STANDARD
import com.dnegu.foodyape.util.isCorrectLatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun LocalizationScreen(
    recipesId: Int,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val recipes by viewModel.recipes.observeAsState(initial = null)
    viewModel.getRecipesById(recipesId)
    Localization(navController,recipes)
}

@Composable
fun Localization(
    navController: NavController,
    recipes: Recipes?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { androidx.compose.material.Text(recipes?.origin?.country ?: "", maxLines = 1, overflow = TextOverflow.Ellipsis) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        },
    ) { pv ->
        Column {
            recipes?.origin?.latlng?.let {
                val strMarker = recipes.origin.latlng.split(",")
                if(strMarker.isCorrectLatLng()){
                    val marker = LatLng(strMarker[0].toDouble(), strMarker[1].toDouble())
                    marker.let{
                        val cameraPositionState = rememberCameraPositionState {
                            position = CameraPosition.fromLatLngZoom(it, ZOOM_STANDARD)
                        }
                        val uiSettings by remember { mutableStateOf(MapUiSettings(zoomControlsEnabled = false))}
                        GoogleMap(
                            modifier = Modifier.fillMaxSize(),
                            cameraPositionState = cameraPositionState,
                            uiSettings = uiSettings,
                            contentDescription = "MapsGoogle"
                        ){
                            Marker(
                                state = MarkerState(position = marker),
                                title = recipes.origin.country,
                                snippet = "Origin ${recipes.name}"
                            )
                        }
                    }
                } else {
                    Text(text = "Datos incorrectos de Latitud y Longitud")
                }
            }
        }
        pv.toString()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodYapeTheme {
        Localization(
            navController = rememberNavController(),
            recipes = RecipesResponse.mock().articles?.firstOrNull())
    }
}