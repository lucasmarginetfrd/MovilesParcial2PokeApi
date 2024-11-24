package com.example.marginetcamposparcial2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PokemonAdapter
    private var pokeList = mutableListOf<PokemonData>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokeTypings = listOf("Please select any typing","Normal", "Fire", "Water", "Grass", "Electric", "Ice",
            "Fighting", "Poison", "Ground", "Flying", "Psychic", "Bug", "Rock", "Ghost", "Dragon",
            "Dark", "Steel", "Fairy")

        val adapterTypings = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pokeTypings)

        recyclerView = findViewById(R.id.rvPokemon)
        recyclerView.layoutManager = LinearLayoutManager(this)
        spinner = findViewById(R.id.spinnerPoke)
        adapter = PokemonAdapter(pokeList)
        recyclerView.adapter = adapter
        spinner.adapter = adapterTypings

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val typing = pokeTypings[position]
                val pokeByType = when (typing) {
                    "Grass" -> pokeByType(pokeList, "grass")
                    "Fire" -> pokeByType(pokeList, "fire")
                    "Water" -> pokeByType(pokeList, "water")
                    "Ice" -> pokeByType(pokeList, "ice")
                    "Electric" -> pokeByType(pokeList, "electric")
                    "Normal" -> pokeByType(pokeList, "normal")
                    "Fighting" -> pokeByType(pokeList, "fighting")
                    "Poison" -> pokeByType(pokeList, "poison")
                    "Ground" -> pokeByType(pokeList, "ground")
                    "Flying" -> pokeByType(pokeList, "flying")
                    "Psychic" -> pokeByType(pokeList, "psychic")
                    "Bug" -> pokeByType(pokeList, "bug")
                    "Rock" -> pokeByType(pokeList, "rock")
                    "Ghost" -> pokeByType(pokeList, "ghost")
                    "Dragon" -> pokeByType(pokeList, "dragon")
                    "Dark" -> pokeByType(pokeList, "dark")
                    "Steel" -> pokeByType(pokeList, "steel")
                    "Fairy" -> pokeByType(pokeList, "fairy")
                    else -> pokeList
                }
                adapter.filterPokes(pokeByType)
                adapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        adapter.onItemClickListener = {pokemon ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("pokemon", pokemon)
            startActivity(intent)
        }

        getPokemon()

    }

    @SuppressLint("NotifyDataSetChanged")
    //datos Pokemon de la primera gen
    private fun getPokemon() {
        CoroutineScope(Dispatchers.IO).launch {
            for (pokeID in 1..151) {
                val call = getRetrofit().create(ApiService::class.java).getPokemon("$pokeID")
                val response = call.body()
                runOnUiThread {
                    if (call.isSuccessful) {
                        val pokeTypes = mutableListOf<String>()
                        val pokeAbilities = mutableListOf<String>()
                        val pokeHidden = mutableListOf<Boolean>()
                        val pokeStats = mutableListOf<Int>()
                        response?.types?.forEach {
                            pokeTypes.add(it.type.name)
                        }
                        response?.abilities?.forEach {
                            pokeAbilities.add(it.ability.name)
                        }
                        response?.abilities?.forEach {
                            pokeHidden.add(it.is_hidden)
                        }
                        response?.stats?.forEach {
                            pokeStats.add(it.base_stat)
                        }


                        val pokemon = PokemonData(
                            id = response?.id,
                            name = response?.name,
                            types = pokeTypes,
                            sprite = response?.sprites?.front_default,
                            offArtwork = response?.sprites?.other?.officialArtwork?.front_default,
                            abilities = pokeAbilities,
                            hidden = pokeHidden,
                            stats = pokeStats
                        )
                        pokeList.add(pokemon)
                        adapter.notifyDataSetChanged()

                    }
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //Filtrar pokemon segun sus tipos
    private fun pokeByType(pokemonList: List<PokemonData>, type: String): List<PokemonData> {
        val pokeFilter =  mutableListOf<PokemonData>()
        for (pokemon in pokemonList) {
            if (pokemon.types?.any { it == type } == true) {
                pokeFilter.add(pokemon)
            }
        }
        return pokeFilter
    }

}