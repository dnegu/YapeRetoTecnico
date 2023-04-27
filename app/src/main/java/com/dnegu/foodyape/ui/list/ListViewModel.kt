package com.dnegu.foodyape.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import com.dnegu.foodyape.domain.model.Recipes
import com.dnegu.foodyape.domain.usecase.GetRecipesListUseCase
import com.dnegu.foodyape.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCase: GetRecipesListUseCase
) : BaseViewModel() {

    private var listRecipes:List<Recipes> = listOf()

    private val _recipesList = MutableLiveData<List<Recipes>>()
    val recipesList: LiveData<List<Recipes>> = _recipesList

    fun getRecipesList() {
        call({
            useCase.getRecipesList()
        }, onSuccess = {
            _recipesList.postValue(it)
            listRecipes = it
        }, onError = {
            error.postValue(it.message)
        })
    }

    fun searchWithParam(value: String){
        if(value.isNotEmpty()){
            val listSearch = listRecipes.filter { recipe ->
                recipe.name.contains(value, true)
            }
            _recipesList.postValue(listSearch)
        } else {
            _recipesList.postValue(listRecipes)
        }
    }

    fun getListRecipesSize() = listRecipes.size
}