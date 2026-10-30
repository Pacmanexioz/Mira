package com.example.mira

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val name = intent.getStringExtra(EXTRA_NAME) ?: "usuario"
        findViewById<TextView>(R.id.tvGreeting).text = "Hola, $name"

        findViewById<android.view.View>(R.id.cardProfile).setOnClickListener {
            Toast.makeText(this, "Perfil: próximamente", Toast.LENGTH_SHORT).show()
        }
        findViewById<android.view.View>(R.id.cardScan).setOnClickListener {
            Toast.makeText(this, "Escanear objeto: próximamente", Toast.LENGTH_SHORT).show()
        }
        findViewById<android.view.View>(R.id.cardClassroom).setOnClickListener {
            startActivity(Intent(this, ClassroomHubActivity::class.java))
        }
        findViewById<android.view.View>(R.id.cardMyClassrooms).setOnClickListener {
            startActivity(Intent(this, ClassroomListActivity::class.java))
        }
        findViewById<android.view.View>(R.id.tvLogout).setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}