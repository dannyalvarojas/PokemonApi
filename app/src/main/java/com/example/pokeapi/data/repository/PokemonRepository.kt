package com.example.pokeapi.data.repository

import com.example.pokeapi.data.entities.PokemonResponse
import com.example.pokeapi.data.remote.PokemonDataSource
import com.example.pokeapi.data.remote.Result
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val remoteDataSource: PokemonDataSource
) {

    suspend fun getPokemonById(id: String): Result<PokemonResponse> = remoteDataSource.getPokemonById(id = id)

    suspend fun getPokemonByName(name: String): Result<PokemonResponse> = remoteDataSource.getPokemonByName(name = name)

}