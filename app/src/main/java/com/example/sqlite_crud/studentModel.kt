package com.example.sqlite_crud

import kotlin.random.Random

data class studentModel (
    var id: Int = getAutoId(),
    var name: String = "",
    var email: String = ""
) {
    companion object {
        fun getAutoId(): Int {
            val random = java.util.Random()
            return random.nextInt(100)
        }
    }
}