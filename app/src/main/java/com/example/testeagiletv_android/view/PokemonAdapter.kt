package com.example.testeagiletv_android.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testeagiletv_android.databinding.ItemListBinding
import com.example.testeagiletv_android.view.model.PokemonListUiModel
import com.squareup.picasso.Picasso

class PokemonAdapter(
    private var pokemonListName: List<PokemonListUiModel>,
    private val listener: PokemonListListener
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindPokemonList(pokemonListItem: PokemonListUiModel) {
            binding.textPokemonName.text = pokemonListItem.name

            val imageUrl = pokemonListItem.imageUrl
            Picasso.get()
                .load(imageUrl)
                .into(binding.imagePokemon)

            binding.PokemonItemList.setOnClickListener {
                listener.onClickPokemon(pokemonListItem.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = ItemListBinding.inflate(inflater, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonItem = pokemonListName[position]
        holder.bindPokemonList(pokemonItem)
    }

    override fun getItemCount(): Int {
        return pokemonListName.size
    }

    fun updatePokemonList(newList: List<PokemonListUiModel>) {
        pokemonListName = newList
        notifyDataSetChanged()
    }

    interface PokemonListListener {
        fun onClickPokemon(name: String)
    }
}
