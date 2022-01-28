package com.example.apis

//--> News contain list of Articles.

data class News(val totalResults: Int, val articles: List<Article>)