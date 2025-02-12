package com.example.testeagiletv_android.model

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any?,
    val results: List<PokemonResult>,
)

data class PokemonResult(
    val name: String,
    val url: String,
)
