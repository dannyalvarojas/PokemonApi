package com.example.pokeapi.res

import com.example.pokeapi.data.entities.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: String): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): Response<PokemonResponse>
}