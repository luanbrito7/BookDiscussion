package com.example.bookdiscussion.dal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookdiscussion.models.Book
import com.example.bookdiscussion.dal.dao.BookDAO

@Database(entities = [Book::class], version = 1)
abstract class BookDB : RoomDatabase() {
    abstract fun bookDAO() : BookDAO

    companion object {
        @Volatile
        private var INSTANCE : BookDB? = null
        fun getInstance(c: Context) : BookDB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }

                val instance = Room.databaseBuilder(
                    c.applicationContext,
                    BookDB::class.java,
                    "books.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}