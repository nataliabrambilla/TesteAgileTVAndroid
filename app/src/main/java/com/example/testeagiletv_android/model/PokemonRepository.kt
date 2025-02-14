package com.example.testeagiletv_android.model

import com.example.testeagiletv_android.model.cache.PokemonDatabase
import com.example.testeagiletv_android.model.cache.PokemonEntity
import com.example.testeagiletv_android.model.response.PokemonFeaturesResponse
import com.example.testeagiletv_android.model.response.PokemonResultResponse

private const val LIST_LIMIT = 20

class PokemonRepository {

    private val remote = RetrofitHelper.pokemonApi
    private val pokemonDao = PokemonDatabase.getDatabase().pokemonDao()

    suspend fun getPokemonListName(page: Int): List<PokemonResultResponse> {
        val cache = pokemonDao.getAll()

        return if (page * LIST_LIMIT >= cache.size) {
            val fetchedList = fetchList(page)
            val map = fetchedList.toEntity()
            pokemonDao.insert(map)
            fetchedList
        } else {
            cache.take((page + 1) * LIST_LIMIT).map {
                PokemonResultResponse(
                    name = it.name,
                    url = it.url
                )
            }
        }
    }

    private fun List<PokemonResultResponse>.toEntity() = mapNotNull {
        PokemonEntity(
            name = it.name,
            url = it.url
        )
    }

    private suspend fun fetchList(page: Int): List<PokemonResultResponse>{
        val response = remote.getPokemonListName(
            limit = LIST_LIMIT,
            offset = LIST_LIMIT * page
        )
        val body = response.body()

        return if(response.isSuccessful && body != null) {
            body.results
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
