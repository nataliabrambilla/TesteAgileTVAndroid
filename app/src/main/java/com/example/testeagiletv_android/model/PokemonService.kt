package com.example.testeagiletv_android.model

import com.example.testeagiletv_android.model.response.PokemonFeaturesResponse
import com.example.testeagiletv_android.model.response.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonListName(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): Response<PokemonListResponse>

    @GET("pokemon/{name}")
    suspend fun getPokemonFeatures(
        @Path("name") name: String
    ): Response<PokemonFeaturesResponse>
}
