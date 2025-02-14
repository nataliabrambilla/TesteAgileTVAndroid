package com.example.testeagiletv_android.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeagiletv_android.model.PokemonRepository
import com.example.testeagiletv_android.view.model.PokemonListUiModel
import kotlinx.coroutines.launch

private const val BASE_IMAGE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/%s.png"

class PokemonListViewModel : ViewModel() {

    private var page = 0

    private val pokemonRepository = PokemonRepository()

    private val _pokemonListName = MutableLiveData<List<PokemonListUiModel>>()
    val pokemonListName: LiveData<List<PokemonListUiModel>> = _pokemonListName

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

                val uiModels = resultPokemonList.results.mapNotNull {
                    val imageUrl = getPokemonImageUrl(it.url) ?: return@mapNotNull null
                    PokemonListUiModel(
                        name = it.name,
                        imageUrl = imageUrl
                    )
                }

                val newList = _pokemonListName.value.orEmpty() + uiModels

                _pokemonListName.postValue(newList)

            } catch (e: Exception) {
                println("Erro ao buscar lista de Pokémons: ${e.message}")
                // tratar erro
            }
        }
    }

    private fun getPokemonImageUrl(url: String): String? {
        val pattern = Regex("""/(\d+)/""")
        val matchResult = pattern.find(url)
        val id =  matchResult?.groupValues?.get(1)?.toIntOrNull() ?: return null
        return BASE_IMAGE_URL.format(id)
    }
}