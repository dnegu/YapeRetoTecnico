package com.dnegu.foodyape.domain.model

data class Recipes(
    val id: Int,
    val imageURL: String,
    val ingredients: List<Ingredient>,
    val name: String,
    val rating : String,
    val origin : Origin,
    val description:String,
    val originalURL: String,
    val steps: List<String>,
    val timers: List<Int>
)