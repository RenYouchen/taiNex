package com.kot.tainex.util

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AccountDB(context : Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VER) {
    companion object {
        private const val DB_NAME = "Account.db"
        private const val DB_VER = 1
        private const val TABLE_NAME = "data"
        private const val COL_ID = "id"
        private const val COL_NAME = "email"
        private const val COL_PASS = "pass"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createDB = ("create table $TABLE_NAME ("+
                "$COL_ID integer primary key autoincrement,"+
                "$COL_NAME TEXT,"+
                "$COL_PASS TEXT)")
        db?.execSQL(createDB)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVer: Int, newVer: Int) {
        val dropTable = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }

    fun addUser(email : String, pass : String): Long {
        val values = ContentValues().apply {
            put(COL_NAME,email)
            put(COL_PASS,pass)
        }
        val db = writableDatabase
        return db.insert(TABLE_NAME,null,values)
    }

    fun readUser(email: String,pass: String):Boolean {
        val db = readableDatabase
        val sqlCmd = "$COL_NAME = ? AND $COL_PASS = ?"
        val data = arrayOf(email,pass)
        val result = db.query(TABLE_NAME,null,sqlCmd,data,null,null,null)
        val correctPassOrEmail = result.count >= 1
        result.close()
        return correctPassOrEmail
    }

    fun checkUser(email: String):Boolean {
        val db = readableDatabase
        val sqlCmd = "$COL_NAME = ?"
        val result = db.query(TABLE_NAME,null,sqlCmd, arrayOf(email),null,null,null)
        val haveUser = result.count >= 1
        result.close()
        return haveUser
    }
}