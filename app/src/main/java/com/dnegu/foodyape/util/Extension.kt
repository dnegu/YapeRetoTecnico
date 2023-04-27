package com.dnegu.foodyape.util

import com.dnegu.foodyape.util.Constants.NUM_DOUBLE

fun List<String>.isCorrectLatLng(): Boolean{
    if(this.size != NUM_DOUBLE) return false
    return try{
        this.forEach { it.toDouble() }
        true
    }catch (e: Exception){
        false
    }
}