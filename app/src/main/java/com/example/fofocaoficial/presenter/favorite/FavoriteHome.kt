package com.example.fofocaoficial.presenter.favorite

import com.example.fofocaoficial.model.Article

interface FavoriteHome {

    interface Presenter {
        fun onSuccess(articles: List<Article>)

    }
}