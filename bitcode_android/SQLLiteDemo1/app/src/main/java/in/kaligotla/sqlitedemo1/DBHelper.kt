package `in`.kaligotla.sqlitedemo1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(var context: Context, var dbName: String, var cursor: SQLiteDatabase.CursorFactory?, var version: Int):
    SQLiteOpenHelper(context, dbName, cursor, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table IF NOT EXISTS Employee(emp_id Int PRIMARY KEY, emp_name Text NOT NULL, emp_salary Int);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}