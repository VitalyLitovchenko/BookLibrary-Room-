package com.example.booklibraryroom.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.booklibraryroom.model.BookInfo

@Dao
interface BookInfoDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBook(book: BookInfo)

    @Update
    suspend fun updateBookInfo(book: BookInfo)

    @Query("SELECT * FROM book_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<BookInfo>>

    @Delete
    fun deleteCurrentBook(book: BookInfo)

    @Query("DELETE FROM book_table")
    suspend fun deleteAllBooks()

}