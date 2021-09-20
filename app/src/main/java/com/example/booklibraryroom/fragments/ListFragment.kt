package com.example.booklibraryroom.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booklibraryroom.R
import com.example.booklibraryroom.adapter.BookInfoAdapter
import com.example.booklibraryroom.viewModel.BookInfoViewModel
import com.example.booklibraryroom.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get()= _binding!!

    private lateinit var mBookInfoViewModel: BookInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBookInfoViewModel = ViewModelProvider(this).get(BookInfoViewModel::class.java)

        val adapter = BookInfoAdapter()

        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        mBookInfoViewModel.readAllData.observe(viewLifecycleOwner, Observer { user ->
            adapter.setData(user)
        })

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuDelete){
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    fun deleteAllUsers(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
            mBookInfoViewModel.deleteAllBooks()
            Toast.makeText(requireContext(), "Successfully Deleted", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){ _, _ ->

        }
        builder.setTitle("Delete all books?")
        builder.setMessage("Are you sure want to delete all Books?")
        builder.create().show()
    }
}