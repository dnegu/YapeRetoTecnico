package com.dnegu.foodyape.data.model

class RequestException(val code: Int, message: String) : Throwable(message)