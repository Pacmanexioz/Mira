package com.example.mira

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ClassroomHubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classroom_hub)

        findViewById<TextView>(R.id.tvHeaderTitle).text = "Aula virtual"
        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }

        findViewById<View>(R.id.btnCreate).setOnClickListener {
            startActivity(Intent(this, CreateClassroomActivity::class.java))
        }
        findViewById<View>(R.id.btnJoin).setOnClickListener { showJoinDialog() }
    }

    private fun showJoinDialog() {
        val view = layoutInflater.inflate(R.layout.dialog_join_classroom, null)
        val til = view.findViewById<TextInputLayout>(R.id.tilCode)
        val et = view.findViewById<TextInputEditText>(R.id.etCode)

        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle("Unirme a un aula")
            .setView(view)
            .setNegativeButton("Cancelar", null)
            .setPositiveButton("Unirme", null)
            .create()
        dialog.show()

        // Se asigna después de show() para que no se cierre si el código es inválido
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
            val classroom = ClassroomRepository.findByCode(et.text.toString())
            if (classroom == null) {
                til.error = "No existe un aula con ese código"
            } else {
                dialog.dismiss()
                val intent = Intent(this, ClassroomDetailActivity::class.java)
                intent.putExtra(ClassroomDetailActivity.EXTRA_CODE, classroom.code)
                startActivity(intent)
            }
        }
    }
}