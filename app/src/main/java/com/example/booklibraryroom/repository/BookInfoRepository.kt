package com.example.booklibraryroom.repository

import androidx.lifecycle.LiveData
import com.example.booklibraryroom.data.BookInfoDao
import com.example.booklibraryroom.model.BookInfo

class BookInfoRepository(private val bookInfoDao: BookInfoDao) {

    val readAllData: LiveData<List<BookInfo>> = bookInfoDao.readAllData()

    suspend fun addBook(bookInfo: BookInfo){
        bookInfoDao.addBook(bookInfo)
    }

    suspend fun updateBook(bookInfo: BookInfo){
        bookInfoDao.updateBookInfo(bookInfo)
    }

    suspend fun deleteCurrentBook(bookInfo: BookInfo){
        bookInfoDao.deleteCurrentBook(bookInfo)
    }

    suspend fun deleteAllBooks(){
        bookInfoDao.deleteAllBooks()
    }
}