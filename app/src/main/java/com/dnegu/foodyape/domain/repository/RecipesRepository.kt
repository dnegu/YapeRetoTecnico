package com.dnegu.foodyape.domain.repository

import com.dnegu.foodyape.domain.model.Recipes

interface RecipesRepository {
    suspend fun getRecipesList(): Result<List<Recipes>>

    suspend fun getRecipesDetail(id: Int): Result<Recipes>
}