package com.example.testeagiletv_android.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {

    @GET("api/v2/pokemon/")
    suspend fun getPokemonList(): Response<PokemonListResponse>

    @GET("api/v2/pokemon/")
    suspend fun getPokemonFeatures(
        @Query("id") id: Int,
    ): Response<PokemonFeaturesResult>

}