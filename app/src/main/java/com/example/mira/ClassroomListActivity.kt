package com.example.mira

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class ClassroomListActivity : AppCompatActivity() {

    private lateinit var adapter: ClassroomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classroom_list)

        findViewById<TextView>(R.id.tvHeaderTitle).text = "Mis aulas"
        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }

        adapter = ClassroomAdapter(ClassroomRepository.classrooms) { classroom ->
            val intent = Intent(this, ClassroomDetailActivity::class.java)
            intent.putExtra(ClassroomDetailActivity.EXTRA_CODE, classroom.code)
            startActivity(intent)
        }
        findViewById<RecyclerView>(R.id.rvClassrooms).adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged() // refresca si se creó un aula nueva
    }
}