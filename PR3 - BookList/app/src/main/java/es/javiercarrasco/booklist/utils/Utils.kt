package es.javiercarrasco.booklist.utils

import android.content.Context
import android.util.Log
import es.javiercarrasco.booklist.R
import es.javiercarrasco.booklist.model.Book
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

fun readRawFile(context: Context): MutableList<Book> {
    val listOfBooks = mutableListOf<Book>()

    try {
        val entrada = InputStreamReader(
            context.resources.openRawResource(R.raw.books)
        )

        val br = BufferedReader(entrada)
        var linea = br.readLine()
        while (!linea.isNullOrEmpty()) {
            Log.i("FILE", linea)

            val (id, isbn, title, author, cover) = linea.split(";")

            listOfBooks.add(
                Book(
                    id = id.toInt(),
                    isbn = isbn,
                    title = title,
                    author = author,
                    cover = cover
                )
            )
            linea = br.readLine()
        }

        br.close()
        entrada.close()
    } catch (e: IOException) {
        Log.e("ERROR IO", e.message.toString())
    }

    return listOfBooks
}