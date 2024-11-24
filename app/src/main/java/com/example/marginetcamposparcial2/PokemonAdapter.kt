package com.example.marginetcamposparcial2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter(private var pokes: List<PokemonData>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    lateinit var onItemClickListener: (PokemonData) -> Unit

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val imViewPoke: ImageView = view.findViewById(R.id.ivPokemon)
        private val tvNomPoke: TextView = view.findViewById(R.id.namePokemon)
        private val tvTypePoke1: TextView = view.findViewById(R.id.typePokemon1)
        private val tvTypePoke2: TextView = view.findViewById(R.id.typePokemon2)

        fun bind(pokemon: PokemonData) {
            tvNomPoke.text = pokemon.name?.capitalize()
            tvTypePoke1.text = pokemon.types?.get(0)?.capitalize()
            if (pokemon.types?.size == 2) { tvTypePoke2.text = pokemon.types[1].capitalize() } else { tvTypePoke2.text = "" }
            Glide.with(view.context).load(pokemon.sprite).into(imViewPoke)

            view.setOnClickListener {
                onItemClickListener(pokemon)
            }
        }
    }

    fun filterPokes(filteredPokes: List<PokemonData>) {
        this.pokes = filteredPokes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return pokes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val poke = pokes[position]
        holder.bind(poke)
    }
}