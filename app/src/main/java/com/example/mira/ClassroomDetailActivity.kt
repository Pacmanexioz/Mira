package com.example.mira

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ClassroomDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CODE = "extra_code"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classroom_detail)

        val classroom = ClassroomRepository.findByCode(intent.getStringExtra(EXTRA_CODE) ?: "")
        if (classroom == null) {
            finish()
            return
        }

        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }
        findViewById<TextView>(R.id.tvClassName).text = classroom.name
        findViewById<TextView>(R.id.tvClassInfo).text =
            "Código: ${classroom.code}  ·  ${classroom.members} integrantes"
        findViewById<TextView>(R.id.tvAvatar).text = classroom.name.first().uppercase()

        findViewById<View>(R.id.cardAnnounce).setOnClickListener {
            Toast.makeText(this, "Próximamente", Toast.LENGTH_SHORT).show()
        }

        // Publicaciones de ejemplo
        val posts = listOf(
            "Bienvenidos a ${classroom.name}" to "Hace 2 días",
            "Nueva tarea: Actividad 1" to "Hace 1 día",
            "Material publicado: Introducción" to "Hoy"
        )
        val container = findViewById<LinearLayout>(R.id.postsContainer)
        posts.forEach { (title, date) ->
            val item = layoutInflater.inflate(R.layout.item_post, container, false)
            item.findViewById<TextView>(R.id.tvPostTitle).text = title
            item.findViewById<TextView>(R.id.tvPostDate).text = date
            container.addView(item)
        }
    }
}