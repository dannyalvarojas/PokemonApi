package com.example.pokeapi.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonResponse(
    val name: String,
    val sprites: Sprite
): Parcelable

@Parcelize
data class Sprite(
    val front_default: String
): Parcelable
