package com.example.fofocaoficial.presenter.news

import com.example.fofocaoficial.model.NewsResponse

interface NewsHome {
    interface Presenter {
        fun requestAl()
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()

    }
}