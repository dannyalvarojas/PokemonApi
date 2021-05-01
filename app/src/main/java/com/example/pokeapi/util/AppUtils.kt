package com.example.pokeapi.util

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AppUtils {
    companion object {

        inline fun <reified T> fromJson(json: String): T {
            return Gson().fromJson(json, object : TypeToken<T>() {}.type)
        }
    }
}