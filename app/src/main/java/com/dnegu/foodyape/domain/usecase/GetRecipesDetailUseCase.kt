package com.dnegu.foodyape.domain.usecase

import com.dnegu.foodyape.domain.model.Recipes
import com.dnegu.foodyape.domain.repository.RecipesRepository
import javax.inject.Inject

class GetRecipesDetailUseCase @Inject constructor(private val recipesRepository: RecipesRepository) {

    suspend fun getRecipesDetail(id: Int): Result<Recipes> {
        return recipesRepository.getRecipesDetail(id)
    }
}

