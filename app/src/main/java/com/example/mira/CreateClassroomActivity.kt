package com.example.mira

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CreateClassroomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_classroom)

        findViewById<TextView>(R.id.tvHeaderTitle).text = "Crear aula"
        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }

        val tilName = findViewById<TextInputLayout>(R.id.tilClassName)
        val tilMembers = findViewById<TextInputLayout>(R.id.tilMembers)
        val etName = findViewById<TextInputEditText>(R.id.etClassName)
        val etMembers = findViewById<TextInputEditText>(R.id.etMembers)

        findViewById<View>(R.id.btnCreateClass).setOnClickListener {
            tilName.error = null
            tilMembers.error = null

            val name = etName.text.toString().trim()
            val members = etMembers.text.toString().toIntOrNull()

            var valid = true
            if (name.isEmpty()) {
                tilName.error = "Ingresa el nombre del aula"; valid = false
            }
            if (members == null || members < 1) {
                tilMembers.error = "Ingresa un número válido"; valid = false
            }

            if (valid) {
                val classroom = ClassroomRepository.add(name, members!!)
                val intent = Intent(this, ClassroomDetailActivity::class.java)
                intent.putExtra(ClassroomDetailActivity.EXTRA_CODE, classroom.code)
                startActivity(intent)
                finish()
            }
        }
    }
}