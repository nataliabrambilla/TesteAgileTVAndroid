package com.example.testeagiletv_android.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testeagiletv_android.databinding.ActivityMainBinding
import com.example.testeagiletv_android.model.PokemonRepository
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var pokemonViewModel: PokemonViewModel

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        pokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        pokemonViewModel.fetchPokemonList()
        setupPokemonList()
        observePokemonList()

        /*lifecycleScope.launch {
            val result = pokemonRepository.getPokemonList().results
            println("natalia: ${result[0].name}")
        }*/

    }

    private fun setupPokemonList() {
        pokemonAdapter = PokemonAdapter(emptyList())
        binding.rvPokemonList.layoutManager = LinearLayoutManager(this)
        binding.rvPokemonList.adapter = pokemonAdapter
    }

    private fun observePokemonList() {
        pokemonViewModel.pokemonList.observe(this) { pokemonModel ->
            val sortedPokemonList = pokemonModel.results.sortedBy {
                it.name
            }
            pokemonAdapter.updatePokemonList(sortedPokemonList)
        }
    }
}