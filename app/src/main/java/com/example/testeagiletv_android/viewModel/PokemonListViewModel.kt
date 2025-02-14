package com.example.testeagiletv_android.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeagiletv_android.PokemonUtil
import com.example.testeagiletv_android.model.PokemonRepository
import com.example.testeagiletv_android.view.model.PokemonListUiModel
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {

    private var page = 0

    private val pokemonRepository = PokemonRepository()

    private var pokemonListSource = setOf<PokemonListUiModel>()

    private val _pokemonListName = MutableLiveData<List<PokemonListUiModel>?>()
    val pokemonListName: LiveData<List<PokemonListUiModel>?> = _pokemonListName

    init {
        fetchPokemonList()
    }

    fun onReachEndOfPokemonsList() {
        page++
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                val resultPokemonList = pokemonRepository.getPokemonListName(page)

                val uiModels = resultPokemonList.mapNotNull {
                    val imageUrl = PokemonUtil.getPokemonImageUrl(it.url) ?: return@mapNotNull null
                    PokemonListUiModel(
                        name = it.name,
                        imageUrl = imageUrl
                    )
                }

                pokemonListSource = pokemonListSource + uiModels

                _pokemonListName.postValue(pokemonListSource.toList())

            } catch (e: Exception) {
                println("Natalia -> e: ${e.message}")
                if (page <= 0) {
                    _pokemonListName.postValue(null)
                }
            }
        }
    }

    fun onTryAgainClick() {
        page = 0
        fetchPokemonList()
    }
}