package es.javiercarrasco.booklist.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import es.javiercarrasco.booklist.R
import es.javiercarrasco.booklist.databinding.ItemBookBinding
import es.javiercarrasco.booklist.model.Book

class BooksAdapter(
    val booksList: MutableList<Book>,
    private val onBookLongClick: (Book, Int) -> Unit
) : RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BooksViewHolder(
        ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).root
    )

    override fun getItemCount() = booksList.size

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.bind(booksList[position])
    }

    inner class BooksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBookBinding.bind(view)

        fun bind(book: Book) {
            binding.tvISBN.text = binding.root.resources.getString(
                R.string.txt_isbn, book.isbn
            )
            binding.tvTitle.text = book.title
            binding.tvAuthor.text = book.author

            Log.d("COVER", book.cover)

            Glide.with(binding.root.context)
                .load(book.cover)
                .transform(FitCenter(), RoundedCorners(20))
                .into(binding.ivCover)

            itemView.setOnLongClickListener {
                onBookLongClick(book, adapterPosition)
                true
            }
        }
    }
}