package com.example.booklibraryroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.booklibraryroom.R
import com.example.booklibraryroom.fragments.ListFragment
import com.example.booklibraryroom.fragments.ListFragmentDirections
import com.example.booklibraryroom.model.BookInfo

class BookInfoAdapter: RecyclerView.Adapter<BookInfoAdapter.BookInfoViewHolder>() {

    private var bookList = emptyList<BookInfo>()

    class BookInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val linearLayout =  itemView.findViewById<LinearLayout>(R.id.mainLinearLayout)
        val bookIdTxt = itemView.findViewById<TextView>(R.id.bookId)
        val bookTitleTxt = itemView.findViewById<TextView>(R.id.bookTitle)
        val bookAuthorTxt = itemView.findViewById<TextView>(R.id.bookAuthor)
        val bookPagesTxt = itemView.findViewById<TextView>(R.id.bookPages)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookInfoViewHolder {
        return BookInfoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row, parent, false))
    }

    override fun onBindViewHolder(holder: BookInfoViewHolder, position: Int) {
        val currentItem = bookList[position]
        holder.bookIdTxt.text = currentItem.id.toString()
        holder.bookTitleTxt.text = currentItem.title
        holder.bookAuthorTxt.text = currentItem.author
        holder.bookPagesTxt.text = currentItem.pages.toString()

        holder.linearLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    fun setData(book: List<BookInfo>){
        this.bookList = book
        notifyDataSetChanged()
    }
}