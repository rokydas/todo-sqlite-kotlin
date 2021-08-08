package com.example.sqlite_crud

import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var sqliteHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        sqliteHelper = MyDatabaseHelper(this)
        btnAdd.setOnClickListener {
            addStudent()
        }

        btnView.setOnClickListener {
            getStudents()
        }

    }

    private fun getStudents() {
        val stdList = sqliteHelper.getAllStudents()
        Log.e("myStudents", "${stdList.size}")
    }

    private fun addStudent() {
        val name = edName.text.toString()
        val email = edEmail.text.toString()

        if(name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please input name and email", Toast.LENGTH_SHORT).show()
        } else {
            val std = studentModel(name = name, email = email)
            val status = sqliteHelper.insertStudent(std)
            Log.d("myStatus", status.toString())
            if(status > -1) {
                Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show()
                clearEditText()
            }else {
                Toast.makeText(this, "Adding record failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearEditText() {
        edName.setText("")
        edEmail.setText("")
        edName.requestFocus()
    }

    private fun initView() {

    }
}