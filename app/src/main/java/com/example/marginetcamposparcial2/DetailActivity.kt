package com.example.marginetcamposparcial2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var pokeArtwork: ImageView
    private lateinit var pokeName: TextView
    private lateinit var pokeType: TextView
    private lateinit var pokeType2: TextView
    private lateinit var pokeAbility: TextView
    private lateinit var pokeAbility2: TextView
    private lateinit var pokeAbility3: TextView
    private lateinit var pokeHP: TextView
    private lateinit var pokeATT: TextView
    private lateinit var pokeDEF: TextView
    private lateinit var pokeSPA: TextView
    private lateinit var pokeSPD: TextView
    private lateinit var pokeSPE: TextView

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val pokemon = intent.getParcelableExtra<PokemonData>("pokemon")

        pokeArtwork = findViewById(R.id.pokeSprite)
        pokeName = findViewById(R.id.pokeName)
        pokeType = findViewById(R.id.pokeType1)
        pokeType2 = findViewById(R.id.pokeType2)
        pokeAbility = findViewById(R.id.pokeAbility1)
        pokeAbility2 = findViewById(R.id.pokeAbility2)
        pokeAbility3 = findViewById(R.id.pokeAbility3)
        pokeHP = findViewById(R.id.pokeHp)
        pokeATT = findViewById(R.id.pokeAtt)
        pokeDEF = findViewById(R.id.pokeDef)
        pokeSPA = findViewById(R.id.pokeSpa)
        pokeSPD = findViewById(R.id.pokeSpd)
        pokeSPE = findViewById(R.id.pokeSpe)


        Glide.with(this).load(pokemon?.offArtwork).into(pokeArtwork)
        pokeName.text = pokemon?.name?.uppercase()
        pokeType.text = pokemon?.types?.get(0)?.capitalize()
        if (pokemon?.types?.size == 2) {
            pokeType2.text = pokemon.types[1].capitalize()
        } else {
            pokeType2.text = ""
        }
        pokeAbility.text = pokemon?.abilities?.get(0)?.capitalize()
        if (pokemon?.abilities?.size == 3) {
            pokeAbility2.text = pokemon.abilities[1].capitalize()
            pokeAbility3.text = pokemon.abilities[2].capitalize()
        } else if (pokemon?.abilities?.size == 2 && pokemon.hidden?.get(1) == false) {
            pokeAbility2.text = pokemon.abilities[1].capitalize()
            pokeAbility3.text = ""
        } else if (pokemon?.abilities?.size == 2 && pokemon.hidden?.get(1) == true) {
            pokeAbility2.text = ""
            pokeAbility3.text = pokemon.abilities[1].capitalize()
        } else {
            pokeAbility2.text = ""
            pokeAbility3.text = ""
        }
        pokeHP.text = "HP: " + pokemon?.stats?.get(0).toString().capitalize()
        pokeATT.text = "ATT: " + pokemon?.stats?.get(1).toString().capitalize()
        pokeDEF.text = "DEF: " + pokemon?.stats?.get(2).toString().capitalize()
        pokeSPA.text = "SPA: " + pokemon?.stats?.get(3).toString().capitalize()
        pokeSPD.text = "SPD: " + pokemon?.stats?.get(4).toString().capitalize()
        pokeSPE.text = "SPE: " + pokemon?.stats?.get(5).toString().capitalize()
    }
}
