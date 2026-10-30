package com.example.mira

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tilUsername = findViewById<TextInputLayout>(R.id.tilUsername)
        val tilFullName = findViewById<TextInputLayout>(R.id.tilFullName)
        val tilEmail = findViewById<TextInputLayout>(R.id.tilEmail)
        val tilPassword = findViewById<TextInputLayout>(R.id.tilRegPassword)

        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etFullName = findViewById<TextInputEditText>(R.id.etFullName)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etRegPassword)

        findViewById<MaterialButton>(R.id.btnRegister).setOnClickListener {
            listOf(tilUsername, tilFullName, tilEmail, tilPassword).forEach { it.error = null }

            val username = etUsername.text.toString().trim()
            val fullName = etFullName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString()

            var valid = true
            if (username.isEmpty()) {
                tilUsername.error = "Enter a username"; valid = false
            }
            if (fullName.isEmpty()) {
                tilFullName.error = "Enter your full name"; valid = false
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                tilEmail.error = "Invalid email"; valid = false
            }
            if (password.length < 6) {
                tilPassword.error = "6 characters as minimum"; valid = false
            }

            if (valid) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(MainActivity.EXTRA_NAME, fullName.substringBefore(" "))
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        findViewById<View>(R.id.tvGoLogin).setOnClickListener { finish() }
    }
}