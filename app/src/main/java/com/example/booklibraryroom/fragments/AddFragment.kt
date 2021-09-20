package com.example.booklibraryroom.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.booklibraryroom.R
import com.example.booklibraryroom.model.BookInfo
import com.example.booklibraryroom.viewModel.BookInfoViewModel
import com.example.booklibraryroom.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get()= _binding!!

    private lateinit var mBookViewModel: BookInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBookViewModel = ViewModelProvider(this).get(BookInfoViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val bookTitle = binding.editTextTitle.text.toString()
        val bookAuthor = binding.editTextAuthor.text.toString()
        val bookPages = binding.editTextPages.text

        if (inputCheck(bookTitle, bookAuthor, bookPages)){
            val book = BookInfo(0, bookTitle, bookAuthor, Integer.parseInt(bookPages.toString()))

            mBookViewModel.addBookInfo(book)
            Toast.makeText(requireContext(), "Successfully added !!!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Fill out all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(bookTitle: String, bookAuthor: String, bookPages: Editable): Boolean{
        return !(TextUtils.isEmpty(bookTitle) && TextUtils.isEmpty(bookAuthor) && bookPages.isEmpty())
    }
}