package com.example.marginetcamposparcial2

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getPokemon(@Url url: String): Response<Pokemon>
}