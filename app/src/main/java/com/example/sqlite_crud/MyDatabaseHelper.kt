package com.example.sqlite_crud

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        val DB_NAME: String = "todo_db"
        val tableName: String = "todos"
        val DB_VERSION = 5
        val ID = "id"
        val TITLE = "title"
        val DESCRIPTION = "description"
        val TBL_STUDENT = "tbl_student"
        val NAME = "name"
        val EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblStudent = ("CREATE TABLE " + TBL_STUDENT + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + EMAIL + " TEXT" + ")")
        db?.execSQL(createTblStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, olderVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TBL_STUDENT")
        onCreate(db)
    }

    fun insertStudent(std: studentModel): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(EMAIL, std.email)

        val success = db.insert(TBL_STUDENT, null, contentValues)
        db.close()
        return success
    }

    fun getAllStudents(): ArrayList<studentModel> {
        val stdList: ArrayList<studentModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_STUDENT"
        val db = this.writableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null, )
        }catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var email: String

        if(cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                val std = studentModel(id = id, name = name, email = email)
                stdList.add(std)
            }while (cursor.moveToFirst())
        }

        return stdList


    }

}