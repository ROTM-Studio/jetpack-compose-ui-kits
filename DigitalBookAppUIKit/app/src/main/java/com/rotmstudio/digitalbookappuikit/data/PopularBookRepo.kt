package com.rotmstudio.digitalbookappuikit.data

object PopularBookRepo {
    fun getPopularBooks(): List<PopularBook> = popularBooks
    fun getPopularBook(bookId: Long) = popularBooks.find { it.id == bookId }!!
}