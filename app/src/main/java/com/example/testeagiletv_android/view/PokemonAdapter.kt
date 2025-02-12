package com.example.testeagiletv_android.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testeagiletv_android.databinding.ItemListBinding
import com.example.testeagiletv_android.model.PokemonResult

class PokemonAdapter(private var pokemonList: List<PokemonResult>): RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindPokemonList(pokemonListItem: PokemonResult) {
            binding.textPokemonName.text = pokemonListItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemListBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bindPokemonList(pokemonList[position])
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun updatePokemonList(newList: List<PokemonResult>) {
        pokemonList = newList
        notifyDataSetChanged()
    }
}