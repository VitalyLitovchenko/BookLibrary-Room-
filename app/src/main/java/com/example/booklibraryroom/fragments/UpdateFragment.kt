package com.example.booklibraryroom.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.booklibraryroom.R
import com.example.booklibraryroom.databinding.FragmentUpdateBinding
import com.example.booklibraryroom.model.BookInfo
import com.example.booklibraryroom.viewModel.BookInfoViewModel

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get()= _binding!!

    private lateinit var mBookInfoViewModel: BookInfoViewModel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBookInfoViewModel = ViewModelProvider(this).get(BookInfoViewModel::class.java)

        binding.editTextTitleUpdate.setText(args.currentBook.title)
        binding.editTextAuthorUpdate.setText(args.currentBook.author)
        binding.editTextPagesUpdate.setText(args.currentBook.pages.toString())

        binding.btnUpdate.setOnClickListener {
            updateItem()
        }

        binding.btnDelete.setOnClickListener {
            deleteBook()
        }

        setHasOptionsMenu(true)
    }

    private fun updateItem() {
        val bookTitle = binding.editTextTitleUpdate.text.toString()
        val bookAuthor = binding.editTextAuthorUpdate.text.toString()
        val bookPages = binding.editTextPagesUpdate.text.toString()

        if (inputCheck(bookTitle, bookAuthor, binding.editTextPagesUpdate.text)){
            val updatedBook = BookInfo(args.currentBook.id, bookTitle, bookAuthor, bookPages.toInt())

            mBookInfoViewModel.updateBookInfo(updatedBook)

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

            Toast.makeText(requireContext(), "Successfully Updated !!!", Toast.LENGTH_LONG).show()

        }else {
            Toast.makeText(requireContext(), "Fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(bookTitle: String, bookAuthor: String, bookPages: Editable): Boolean{
        return !(TextUtils.isEmpty(bookTitle) && TextUtils.isEmpty(bookAuthor) && bookPages.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuDelete){
            deleteBook()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteBook() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mBookInfoViewModel.deleteCurrentBook(args.currentBook)
            Toast.makeText(requireContext(), "Successfully Deleted ${args.currentBook.title}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){ _, _ ->

        }
        builder.setTitle("Delete ${args.currentBook.title}?")
        builder.setMessage("Are you sure want to delete ${args.currentBook.title}?")
        builder.create().show()
    }

}