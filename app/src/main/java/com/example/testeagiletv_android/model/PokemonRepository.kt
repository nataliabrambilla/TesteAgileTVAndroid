package com.example.testeagiletv_android.model

import com.example.testeagiletv_android.model.response.PokemonFeaturesResponse
import com.example.testeagiletv_android.model.response.PokemonListResponse

private const val LIST_LIMIT = 20

class PokemonRepository {

    private val remote = RetrofitHelper.pokemonApi

    suspend fun getPokemonListName(page: Int): PokemonListResponse {
        val response = remote.getPokemonListName(
            limit = LIST_LIMIT,
            offset = LIST_LIMIT * page
        )
        val body = response.body()
        return if(response.isSuccessful && body != null) {
            body
        } else {
            throw Exception()
        }
    }

    suspend fun getPokemonFeatures(name: String): PokemonFeaturesResponse {
        val response = remote.getPokemonFeatures(name)
        val body = response.body()
        return if(response.isSuccessful && body != null) {
            body
        } else {
            throw Exception()
        }
    }
}
