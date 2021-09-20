package com.example.booklibraryroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booklibraryroom.model.BookInfo

@Database(entities = [BookInfo::class], version = 1, exportSchema = false)
abstract class BookInfoDatabase: RoomDatabase() {

    abstract fun bookInfoDao(): BookInfoDao

    companion object{
        @Volatile
        private var INSTANCE: BookInfoDatabase? = null

        fun getDatabase(context: Context): BookInfoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookInfoDatabase::class.java,
                    "bookLibrary_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}