package com.example.testeagiletv_android.view.model

data class PokemonDetailModel(
    val name: String,
    val imageURL: String,
    val types: List<String>,
    val height: Int,
    val weight: Int,
    val abilities: List<String>,
    val stats: List<Stats>
)

data class Stats(
    val statName: String,
    val statNumber: Int,
)
