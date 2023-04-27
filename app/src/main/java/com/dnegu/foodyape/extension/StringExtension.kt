package com.dnegu.foodyape.extension

class StringExtension {

    fun String.capitalizeFirstChar(): String {
        return replaceFirstChar {
            it.uppercaseChar()
        }
    }
}