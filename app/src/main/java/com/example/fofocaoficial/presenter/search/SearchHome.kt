package com.example.fofocaoficial.presenter.search

import com.example.fofocaoficial.model.NewsResponse

interface SearchHome {
    interface Presenter{
        fun search(term: String)
        fun onSuccess(newsResponse: NewsResponse)
        fun onError(message: String)
        fun onComplete()
    }
}