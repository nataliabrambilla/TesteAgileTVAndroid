package com.example.testeagiletv_android.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeagiletv_android.model.PokemonListResponse
import com.example.testeagiletv_android.model.PokemonRepository
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {

    private val pokemonRepository = PokemonRepository()

    private val _pokemonList = MutableLiveData<PokemonListResponse>()
    val pokemonList: LiveData<PokemonListResponse> = _pokemonList

    fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                val resultPokemonList = pokemonRepository.getPokemonList()
                _pokemonList.postValue(resultPokemonList)
            } catch (e: Exception) {
                println("NATALIA -> Erro pokemonRepository.getPokemonList() ")
                // tratar erro
            }
        }
    }
}