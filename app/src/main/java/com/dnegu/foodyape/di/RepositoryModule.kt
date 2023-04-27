package com.dnegu.foodyape.di

import com.dnegu.foodyape.data.repository.RecipesRepositoryImpl
import com.dnegu.foodyape.domain.repository.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRecipesRepository(recipesRepositoryImpl: RecipesRepositoryImpl): RecipesRepository
}