package com.example.mira

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClassroomAdapter(
    private val items: List<Classroom>,
    private val onClick: (Classroom) -> Unit
) : RecyclerView.Adapter<ClassroomAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvName)
        val code: TextView = view.findViewById(R.id.tvCode)
        val members: TextView = view.findViewById(R.id.tvMembers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_classroom, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.name.text = item.name
        holder.code.text = "Código: ${item.code}"
        holder.members.text = "${item.members} integrantes"
        holder.itemView.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = items.size
}