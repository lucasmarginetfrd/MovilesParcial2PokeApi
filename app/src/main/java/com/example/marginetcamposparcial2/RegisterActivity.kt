package com.example.marginetcamposparcial2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var buttonContinueToLogin : Button
    private lateinit var editTextEnterName: EditText
    private lateinit var editTextEnterPassword: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonContinueToLogin = findViewById(R.id.buttonToLogin)
        editTextEnterName = findViewById(R.id.editTextName)
        editTextEnterPassword = findViewById(R.id.editTextPassword)

        buttonContinueToLogin.setOnClickListener {
            if (editTextEnterName.text.isNullOrEmpty() || editTextEnterPassword.text.isNullOrEmpty()) {
                Toast.makeText(this, "Ingrese user y password", Toast.LENGTH_SHORT).show()
            } else {
                val userName = editTextEnterName.text.toString()
                val password = editTextEnterPassword.text.toString()

                val preferences = getSharedPreferences("loginPref", MODE_PRIVATE)
                val editor = preferences.edit()
                editor.putString("name", userName)
                editor.putString("pass", password)
                editor.apply()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}