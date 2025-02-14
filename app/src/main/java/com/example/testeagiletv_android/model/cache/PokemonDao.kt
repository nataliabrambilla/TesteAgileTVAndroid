package com.example.testeagiletv_android.model.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon_table")
    suspend fun getAll(): List<PokemonEntity>
}
