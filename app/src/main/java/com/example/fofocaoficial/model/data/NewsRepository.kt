package com.example.fofocaoficial.model.data

import com.example.fofocaoficial.model.Article
import com.example.fofocaoficial.model.db.ArticleDatabase


class NewsRepository(private val db: ArticleDatabase) {

    suspend fun updateInsert(article: Article) = db.getArticleDao().updateInsert(article)

    fun getAll(): List<Article> = db.getArticleDao().getAll()

    suspend fun delete(article: Article) = db.getArticleDao().delete(article)

}