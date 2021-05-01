package com.example.pokeapi.data.remote

import com.example.pokeapi.res.ApiService
import javax.inject.Inject

class PokemonDataSource @Inject constructor(
    private val service: ApiService
): BaseDataSource() {

    suspend fun getPokemonById(id: String) = safeApiCall { service.getPokemonById(id = id) }

    suspend fun getPokemonByName(name: String) = safeApiCall { service.getPokemonByName(name = name) }

}