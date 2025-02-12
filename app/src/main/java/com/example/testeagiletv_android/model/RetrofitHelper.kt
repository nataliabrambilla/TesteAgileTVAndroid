package com.example.testeagiletv_android.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object {

     val pokemonApi = Retrofit.Builder()
         .baseUrl("https://pokeapi.co/")
         .addConverterFactory(GsonConverterFactory.create())
         .build()
         .create(PokemonService::class.java)
    }
}