package es.javiercarrasco.booklist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import es.javiercarrasco.booklist.adapters.BooksAdapter
import es.javiercarrasco.booklist.databinding.ActivityMainBinding
import es.javiercarrasco.booklist.model.Book
import es.javiercarrasco.booklist.utils.readRawFile

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var listOfBooks = mutableListOf<Book>()

    private lateinit var bookAdapter: BooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listOfBooks = readRawFile(this)

        // Comprobación de que se ha leído el fichero y hat libros que mostrar.
        if (listOfBooks.size > 0) {
            Log.d("LIST", listOfBooks.toString())

            bookAdapter = BooksAdapter(listOfBooks,
                onBookLongClick = { book, position ->
                    val bookAux = book
                    listOfBooks.remove(book)
                    bookAdapter.notifyItemRemoved(position)

                    Snackbar.make(
                        binding.root,
                        getString(R.string.txt_book_deleted, bookAux.title),
                        Snackbar.LENGTH_LONG
                    ).setAction(R.string.txt_undo) {
                        listOfBooks.add(position, bookAux)
                        bookAdapter.notifyItemInserted(position)
                    }.show()

                    // Comprobación de que hay libros que mostrar.
                    if (listOfBooks.size == 0) binding.textView.visibility = View.VISIBLE
                    else binding.textView.visibility = View.GONE
                })
            binding.recyclerView.adapter = bookAdapter
        } else binding.textView.visibility = View.VISIBLE
    }
}