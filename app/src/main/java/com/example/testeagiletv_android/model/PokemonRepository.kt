package com.example.testeagiletv_android.model

class PokemonRepository {

    private val remote = RetrofitHelper.pokemonApi

    suspend fun getPokemonList(): PokemonListResponse {
        val response = remote.getPokemonList()
        val body = response.body()
        return if(response.isSuccessful && body != null) {
            body
        } else {
            throw Exception()
        }
    }
}