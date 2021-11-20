package com.ducdiep.newsapp.models

data class ResponseNews(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)