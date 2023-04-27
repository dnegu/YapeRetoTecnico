package com.dnegu.foodyape.data.repository

import com.dnegu.foodyape.data.model.RequestException
import com.dnegu.foodyape.data.service.RecipesService
import com.dnegu.foodyape.domain.model.Recipes
import com.dnegu.foodyape.domain.repository.RecipesRepository
import java.net.HttpURLConnection
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesService: RecipesService
) : RecipesRepository {

    private val cachedList: MutableList<Recipes> = mutableListOf()

    override suspend fun getRecipesList(): Result<List<Recipes>> {
        val apiResponse = recipesService.getRecipes().body()
        if (apiResponse?.status == "OK") {
            val recipesList = apiResponse.articles ?: emptyList()
            cachedList.addAll(recipesList)
            return Result.success(recipesList)
        }

        return Result.failure(
            RequestException(
                code = HttpURLConnection.HTTP_INTERNAL_ERROR,
                message = "An error occurred!"
            )
        )
    }

    override suspend fun getRecipesDetail(id: Int): Result<Recipes> {
        return cachedList.find { it.id == id }?.let { recipes ->
            Result.success(recipes)
        } ?: run {
            Result.failure(Exception("An error occurred when get new detail"))
        }
    }
}