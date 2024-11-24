package com.example.marginetcamposparcial2

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Pokemon(
    val id: Int,
    val name: String,
    val types: List<Types>,
    val sprites: Sprites,
    val abilities: List<Abilities>,
    val stats: List<Stats>
)

@Parcelize
data class PokemonData(
    val id: Int?,
    val name: String?,
    val types: List<String>?,
    val sprite: String?,
    val offArtwork: String?,
    val abilities: List<String>?,
    val hidden: List<Boolean>?,
    val stats: List<Int>?
): Parcelable

data class Types (
    val type: Type
)

data class Type (
    val name: String
)

data class Sprites (
    val front_default: String,
    val other: Other
)

data class Abilities (
    val ability: Ability,
    val is_hidden: Boolean
)

data class Ability (
    val name: String
)

data class Other (
    @SerializedName ("official-artwork") val officialArtwork: OfficialArtwork
)

data class OfficialArtwork (
    val front_default: String
)

data class Stats (
    val base_stat: Int
)