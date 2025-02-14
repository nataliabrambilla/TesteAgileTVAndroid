package com.example.testeagiletv_android.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testeagiletv_android.databinding.ActivityMainBinding
import com.example.testeagiletv_android.viewModel.PokemonListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var pokemonListViewModel: PokemonListViewModel

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        pokemonListViewModel = ViewModelProvider(this)[PokemonListViewModel::class.java]
        setupPokemonList()
        observePokemonList()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.rvPokemonList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!binding.rvPokemonList.canScrollVertically(1)) {
                    pokemonListViewModel.onReachEndOfPokemonsList()
                }
            }
        })

        binding.errorScreen.textTryAgain.setOnClickListener {
            pokemonListViewModel.onTryAgainClick()
        }
    }

    private fun setupPokemonList() {
        pokemonAdapter = PokemonAdapter(emptyList(), object : PokemonAdapter.PokemonListListener {
            override fun onClickPokemon(name: String) {
                navigateToPokemonDetails(name)
            }
        })
        binding.rvPokemonList.layoutManager = GridLayoutManager(this, 2)
        binding.rvPokemonList.adapter = pokemonAdapter
    }

    private fun observePokemonList() {
        pokemonListViewModel.pokemonListName.observe(this) { pokemons ->

            if (pokemons == null) {
                binding.rvPokemonList.visibility = View.GONE
                binding.errorScreen.container.visibility = View.VISIBLE
            } else {
                binding.rvPokemonList.visibility = View.VISIBLE
                binding.errorScreen.container.visibility = View.GONE
            }
            pokemonAdapter.updatePokemonList(pokemons.orEmpty())
        }
    }

    private fun navigateToPokemonDetails(pokemonName: String) {
        val intent = Intent(this@MainActivity, PokemonDetailActivity::class.java)
        intent.putExtra("POKEMON_NAME", pokemonName)
        startActivity(intent)
    }
}
