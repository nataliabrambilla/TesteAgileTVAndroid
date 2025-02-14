package com.example.testeagiletv_android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testeagiletv_android.R
import com.example.testeagiletv_android.databinding.ActivityPokemonDetailBinding
import com.example.testeagiletv_android.view.model.PokemonDetailModel
import com.example.testeagiletv_android.viewModel.PokemonDetailViewModel
import com.squareup.picasso.Picasso

class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var pokemonDetailsViewModel: PokemonDetailViewModel

    private val binding by lazy {
        ActivityPokemonDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        pokemonDetailsViewModel = ViewModelProvider(this)[PokemonDetailViewModel::class.java]
        setUpObserver()
        fetchPokemonDetail()
    }

    private fun fetchPokemonDetail() {
        val pokemonName = intent.getStringExtra("POKEMON_NAME") ?: ""
        pokemonDetailsViewModel.fetchPokemonDetail(pokemonName)
    }

    private fun setUpObserver() {
        pokemonDetailsViewModel.pokemonDetails.observe(this) {
            Picasso.get().load(it.imageURL).into(binding.imagePokemonDetail)
            binding.textNamePokemonDetail.text = it.name
            bindPokemonTypes(it)
            bindHeightDescription(it)
            bindWeightDescription(it)
            bindPokemonAbilities(it)
            bindHp(it)
            bindAttack(it)
            bindDefense(it)
            bindSpecialAttack(it)
            bindSpecialDefense(it)
            bindSpeed(it)
        }
    }

    private fun bindPokemonTypes(pokemon: PokemonDetailModel) {
        val types = pokemon.types.joinToString(separator = "  |  ")
        binding.textTypes.text = types
    }

    private fun bindHeightDescription(pokemon: PokemonDetailModel) {
        val finalHeightMeters = ((pokemon.height) / 10.0).toString()
        binding.textHeightDescription.text = getString(R.string.height_description, finalHeightMeters)
    }

    private fun bindWeightDescription(pokemon: PokemonDetailModel) {
        val finalWeightKilograms = ((pokemon.weight) / 10.0).toString()
        binding.textWeightDescription.text = getString(R.string.weight_description, finalWeightKilograms)
    }

    private fun bindPokemonAbilities(pokemon: PokemonDetailModel) {
        val types = pokemon.abilities.joinToString(separator = "  |  ")
        binding.textAbilities.text = types
    }

    private fun bindHp(pokemon: PokemonDetailModel) {
        var hpStats: Int? = null
        pokemon.stats.forEach {
            if (it.statName.equals("hp", ignoreCase = true)) {
                hpStats = it.statNumber
            }
        }
        binding.progressBarHp.progress = hpStats ?: 0
        binding.textHpStatsNumber.text = hpStats.toString()
    }

    private fun bindAttack(pokemon: PokemonDetailModel) {
        var attackStats: Int? = null
        pokemon.stats.forEach {
            if (it.statName.equals("attack", ignoreCase = true)) {
                attackStats = it.statNumber
            }
        }
        binding.progressBarAttack.progress = attackStats ?: 0
        binding.textAttackStatsNumber.text = attackStats.toString()
    }

    private fun bindDefense(pokemon: PokemonDetailModel) {
        var defenseStats: Int? = null
        pokemon.stats.forEach {
            if (it.statName.equals("defense", ignoreCase = true)) {
                defenseStats = it.statNumber
            }
        }
        binding.progressBarDefense.progress = defenseStats ?: 0
        binding.textDefenseStatsNumber.text = defenseStats.toString()
    }

    private fun bindSpecialAttack(pokemon: PokemonDetailModel) {
        var specialAttackStats: Int? = null
        pokemon.stats.forEach {
            if (it.statName.equals("special-attack", ignoreCase = true)) {
                specialAttackStats = it.statNumber
            }
        }
        binding.progressBarSpecialAttack.progress = specialAttackStats ?: 0
        binding.textSpecialAttackStatsNumber.text = specialAttackStats.toString()
    }

    private fun bindSpecialDefense(pokemon: PokemonDetailModel) {
        var specialDefenseStats: Int? = null
        pokemon.stats.forEach {
            if (it.statName.equals("special-defense", ignoreCase = true)) {
                specialDefenseStats = it.statNumber
            }
        }
        binding.progressBarSpecialDefense.progress = specialDefenseStats ?: 0
        binding.textSpecialDefenseStatsNumber.text = specialDefenseStats.toString()
    }

    private fun bindSpeed(pokemon: PokemonDetailModel) {
        var speedStats: Int? = null
        pokemon.stats.forEach {
            if (it.statName.equals("speed", ignoreCase = true)) {
                speedStats = it.statNumber
            }
        }
        binding.progressBarSpeed.progress = speedStats ?: 0
        binding.textSpeedStatsNumber.text = speedStats.toString()
    }
}
