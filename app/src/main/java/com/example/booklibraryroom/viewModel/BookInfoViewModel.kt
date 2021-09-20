package com.example.booklibraryroom.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.booklibraryroom.data.BookInfoDatabase
import com.example.booklibraryroom.repository.BookInfoRepository
import com.example.booklibraryroom.model.BookInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ThreadPoolExecutor

class BookInfoViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<BookInfo>>
    private val repository: BookInfoRepository


    init{
        val bookInfoDao = BookInfoDatabase.getDatabase(application).bookInfoDao()
        repository = BookInfoRepository(bookInfoDao)
        readAllData = repository.readAllData
    }

    fun addBookInfo(bookInfo: BookInfo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(bookInfo)
        }
    }

    fun updateBookInfo(bookInfo: BookInfo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBook(bookInfo)
        }
    }

    fun deleteCurrentBook(bookInfo: BookInfo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteCurrentBook(bookInfo)
        }
    }

    fun deleteAllBooks(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllBooks()
        }
    }

}