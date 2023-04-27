package com.dnegu.foodyape.domain.usecase

import com.dnegu.foodyape.domain.model.Recipes
import com.dnegu.foodyape.domain.repository.RecipesRepository
import javax.inject.Inject

class GetRecipesListUseCase @Inject constructor(private val recipesRepository: RecipesRepository) {

    suspend fun getRecipesList(): Result<List<Recipes>> {
        return recipesRepository.getRecipesList()
    }
}