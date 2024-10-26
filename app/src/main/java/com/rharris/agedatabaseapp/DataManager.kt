package com.rharris.agedatabaseapp

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataManager(context: Context) {

    // actual database
    private val db: SQLiteDatabase

    init {
        // create instance of our internal
        // CustomSQLiteOpenHelper class
        val helper = CustomSQLiteOpenHelper(context)
        // get writable database
        db = helper.writableDatabase
    }

    companion object {

        /*
        we have const string for each row/table
        that we need to refer to both inside and
        outside the class
         */
        const val TABLE_ROW_ID = "_id"
        const val TABLE_ROW_NAME = "name"
        const val TABLE_ROW_AGE = "age"

        /*
        we have a private const strings for each
        row/table that we need to refer to just
        inside the class
         */
        private const val DB_NAME = "address_book_db"
        private const val DB_VERSION = 1
        private const val TABLE_N_AND_A = "names_and_addresses"
    }

    // below are all our helper methods

    // insert a record
    fun insert(name: String, age: String) {
        // add  details to the table
        val query = "INSERT INTO " + TABLE_N_AND_A + " (" +
                TABLE_ROW_NAME + ", " +
                TABLE_ROW_AGE +
                ") " +
                "VALUES (" +
                "'" + name + "'" + ", " +
                "'" + age + "'" +
                ");"

        Log.i("insert() = ", query)

        db.execSQL(query)
    }

    // delete a record
    fun delete(name: String) {

        // delete details from the table
        // if already exists
        val query = "DELETE FROM " + TABLE_N_AND_A +
                " WHERE " + TABLE_ROW_NAME +
                " = '" + name + "';"

        Log.i("delete() = ", query)

        db.execSQL(query)

    }

    // get all records
    fun selectAll(): Cursor {
        return db.rawQuery("SELECT *" + " from " +
                TABLE_N_AND_A, null)
    }

    // find specific record
    fun searchName(name: String): Cursor {
        val query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_NAME +
                ", " + TABLE_ROW_AGE +
                " from " +
                TABLE_N_AND_A + " WHERE " +
                TABLE_ROW_NAME + " = '" + name + "';"

        Log.i("searchName() = ", query)

        return db.rawQuery(query, null)
    }

    // class is created when DataManager is initialized
    private inner class CustomSQLiteOpenHelper(
        context: Context)
        : SQLiteOpenHelper(
        context, DB_NAME,
        null, DB_VERSION) {

        // function only runs first
        // time the database is created
        override fun onCreate(db: SQLiteDatabase) {

            // create table for photos and their details
            val newTableQueryString = ("create table "
                    + TABLE_N_AND_A + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_NAME
                    + " text not null,"
                    + TABLE_ROW_AGE
                    + " text not null);")

            db.execSQL(newTableQueryString)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            TODO("Not yet implemented")
        }
    }
}