package es.javiercarrasco.booklist.model

data class Book(
    val id: Int,
    val isbn: String,
    val title: String,
    val author: String,
    val cover: String
)