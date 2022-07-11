package com.example.fofocaoficial.presenter

import com.example.fofocaoficial.model.Article

interface ViewHome {
    interface View {
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showArticles(articles: List<Article>)
    }

    interface Favorite {
        fun showArticles(articles: List<Article>)
    }
}