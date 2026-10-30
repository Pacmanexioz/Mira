package com.example.mira

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private val defaultUser = "admin"
    private val defaultPass = "admin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tilUser = findViewById<TextInputLayout>(R.id.tilUser)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilPassword)
        val etUser = findViewById<TextInputEditText>(R.id.etUser)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            tilUser.error = null
            tilPassword.error = null

            val user = etUser.text.toString().trim()
            val pass = etPassword.text.toString()

            when {
                user.isEmpty() -> tilUser.error = "Enter your username"
                pass.isEmpty() -> tilPassword.error = "Enter your password"
                user == defaultUser && pass == defaultPass -> {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra(MainActivity.EXTRA_NAME, user)
                    startActivity(intent)
                    finish()
                }
                else -> tilPassword.error = "Invalid credentials"
            }
        }

        findViewById<android.view.View>(R.id.tvGoRegister).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}