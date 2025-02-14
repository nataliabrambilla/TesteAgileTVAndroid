package com.example.testeagiletv_android.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeagiletv_android.model.PokemonRepository
import com.example.testeagiletv_android.view.model.PokemonDetailModel
import com.example.testeagiletv_android.view.model.Stats
import kotlinx.coroutines.launch

class PokemonDetailViewModel : ViewModel() {

    private val pokemonRepository = PokemonRepository()

    private val _pokemonDetails = MutableLiveData<PokemonDetailModel>()
    val pokemonDetails: LiveData<PokemonDetailModel> = _pokemonDetails

    fun fetchPokemonDetail(pokemonName: String) {
        viewModelScope.launch {
            val resultPokemonDetail = pokemonRepository.getPokemonFeatures(pokemonName)

            val pokemonDetailUiModel = PokemonDetailModel(
                name = resultPokemonDetail.name,
                imageURL = resultPokemonDetail.sprites.other.officialArtwork.frontDefault,
                types = resultPokemonDetail.types.map { it.type.name },
                height = resultPokemonDetail.height,
                weight = resultPokemonDetail.weight,
                abilities = resultPokemonDetail.abilities.map { it.ability.name },
                stats = resultPokemonDetail.stats.map { stat ->
                    Stats(
                        statNumber = stat.baseStat,
                        statName = stat.stat.name,
                    )
                },
            )

            _pokemonDetails.postValue(pokemonDetailUiModel)
        }
    }
}
