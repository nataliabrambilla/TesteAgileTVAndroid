package com.example.testeagiletv_android.model.response

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<PokemonResultResponse>,
)

data class PokemonResultResponse(
    val name: String,
    val url: String,
)
