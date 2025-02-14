package com.example.testeagiletv_android

import android.app.Application
import com.example.testeagiletv_android.model.cache.PokemonDatabase

class PokemonApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        PokemonDatabase.createDatabase(this)
    }
}
