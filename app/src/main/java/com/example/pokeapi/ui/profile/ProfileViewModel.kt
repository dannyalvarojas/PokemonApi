package com.example.pokeapi.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.pokeapi.data.entities.PokemonResponse
import com.example.pokeapi.data.remote.Result
import com.example.pokeapi.data.repository.PokemonRepository
import com.example.pokeapi.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: PokemonRepository
) : BaseViewModel() {

    private val _pokemon = MutableLiveData<PokemonResponse>()
    val pokemon: LiveData<PokemonResponse> = _pokemon

    fun getPokemon(name: String) {

        _isViewLoading.postValue(true)

        viewModelScope.launch {
            val result: Result<PokemonResponse> = withContext(Dispatchers.IO) {
                repository.getPokemonByName(name = name)
            }

            _isViewLoading.postValue(false)
            when (result) {
                is Result.Success -> {
                    _pokemon.value = result.data
                }
            }
        }
    }
}