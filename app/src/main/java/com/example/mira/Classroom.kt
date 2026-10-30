package com.example.mira

data class Classroom(
    val name: String,
    val members: Int,
    val code: String
)

object ClassroomRepository {

    val classrooms = mutableListOf(
        Classroom(name = "Networking", members = 30, code = "NET123")
    )

    fun add(name: String, members: Int): Classroom {
        val chars = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789"
        val code = (1..6).map { chars.random() }.joinToString("")
        val classroom = Classroom(name, members, code)
        classrooms.add(classroom)
        return classroom
    }

    fun findByCode(code: String): Classroom? =
        classrooms.find { it.code.equals(code.trim(), ignoreCase = true) }
}