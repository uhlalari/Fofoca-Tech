package com.example.fofocaoficial.presenter.news


import com.example.fofocaoficial.model.NewsResponse
import com.example.fofocaoficial.model.data.NewsDataSource
import com.example.fofocaoficial.presenter.ViewHome

class NewsPresenter(
    val view: ViewHome.View,
    private val dataSource: NewsDataSource
) : NewsHome.Presenter {

    override fun requestAl() {
        this.view.showProgressBar()
        this.dataSource.getBreakingNews(this)
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        this.view.showArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}

//criamos a abstract activity para deixar o código mais reutilizável