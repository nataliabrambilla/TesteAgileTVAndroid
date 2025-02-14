package com.example.testeagiletv_android.model.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class PokemonEntity(
    @PrimaryKey val name: String,
    val url: String,
)
