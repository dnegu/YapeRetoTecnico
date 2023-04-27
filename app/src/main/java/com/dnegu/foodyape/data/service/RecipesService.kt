package com.dnegu.foodyape.data.service

import com.dnegu.foodyape.data.model.response.RecipesResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecipesService {
    @GET("foods")
    suspend fun getRecipes(): Response<RecipesResponse>
}