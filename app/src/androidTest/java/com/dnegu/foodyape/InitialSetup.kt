package com.dnegu.foodyape

import com.dnegu.foodyape.data.repository.RecipesRepositoryImpl
import com.dnegu.foodyape.data.service.RecipesService
import com.dnegu.foodyape.domain.usecase.GetRecipesDetailUseCase
import com.dnegu.foodyape.domain.usecase.GetRecipesListUseCase
import com.dnegu.foodyape.ui.detail.DetailViewModel
import com.dnegu.foodyape.ui.list.ListViewModel
import com.google.android.gms.maps.model.LatLng
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object InitialSetup {

    private const val BASE_URL = "https://demo5430267.mockable.io/"

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient())
        .baseUrl(BASE_URL)
        .build()

    private fun getRecipeService(): RecipesService = getRetrofit().create(RecipesService::class.java)

    private fun getRecipeRepository() = RecipesRepositoryImpl(getRecipeService())

    private fun getGetRecipesListUseCase() = GetRecipesListUseCase(getRecipeRepository())

    private fun getGetRecipesDetailUseCase() = GetRecipesDetailUseCase(getRecipeRepository())

    fun getListViewModel() = ListViewModel(getGetRecipesListUseCase())

    fun getDetailViewModel() = DetailViewModel(getGetRecipesDetailUseCase())
}

val hasValidApiKey: Boolean =
    BuildConfig.API_KEY.isNotBlank() && BuildConfig.API_KEY != "AIzaSyAXGa9DZ1plrz4NJaN2s5MATdGbCCPt5og"

const val assertRoundingError: Double = 0.01

fun LatLng.assertEquals(other: LatLng) {
    assertEquals(this.latitude, other.latitude, assertRoundingError)
    assertEquals(longitude, other.longitude, assertRoundingError)
}

fun LatLng.assertNotEquals(other: LatLng) {
    assertNotEquals(latitude, other.latitude, assertRoundingError)
    assertNotEquals(longitude, other.longitude, assertRoundingError)
}