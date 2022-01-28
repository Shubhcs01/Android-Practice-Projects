package com.example.apis

//--> News contain list of Articles.

data class News(val totalResults: String, val articles: List<Article>)