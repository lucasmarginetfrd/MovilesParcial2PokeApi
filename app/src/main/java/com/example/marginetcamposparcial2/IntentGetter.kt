package com.example.marginetcamposparcial2

import android.content.Context
import android.content.Intent

class IntentGetter {
    fun getIntent(sprite: String, name: String, type1: String, type2: String, context: Context) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("sprite", sprite)
        intent.putExtra("name", name)
        intent.putExtra("type1", type1)
        intent.putExtra("type2", type2)
        context.startActivity(intent)
    }
}