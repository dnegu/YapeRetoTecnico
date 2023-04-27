package com.dnegu.foodyape.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import com.dnegu.foodyape.domain.model.Recipes
import com.dnegu.foodyape.domain.usecase.GetRecipesDetailUseCase
import com.dnegu.foodyape.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetRecipesDetailUseCase
) : BaseViewModel() {

    private val _showSmartMode = MutableLiveData(true)
    val showSmartMode: LiveData<Boolean> = _showSmartMode

    private val _recipes = MutableLiveData<Recipes>()
    val recipes: LiveData<Recipes> = _recipes

    fun getRecipesById(recipesId: Int) {
        call({
            useCase.getRecipesDetail(recipesId)
        }, onSuccess = {
            _recipes.postValue(it)
        }, onError = {
            onCallError(it)
        })
    }

    fun toggleMode() {
        _showSmartMode.value = !_showSmartMode.value!!
    }
}