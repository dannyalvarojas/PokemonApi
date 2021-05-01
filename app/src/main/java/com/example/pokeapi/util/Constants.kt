package com.example.pokeapi.util

object Constants {

    //Web Service IP
    const val BASE_URL = "https://pokeapi.co/api/v2/"
    const val GENERAL_EMPTY_TEXT = 0
    const val REST_TIMEOUT = 25.toLong()
    const val GENERAL_LOG_APP_TAG = "POKEAPI_LOG"

    //REST String Responses
    const val RESPONSE_SERVICE_OK = 200
    const val BAD_REQUEST = 400
    const val NOT_FOUND = 404
    const val INT_SRV_ERROR = 500
    const val UNAUTHORIZED = 401

}