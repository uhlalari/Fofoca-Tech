package com.example.fofocaoficial.model.data

import android.content.Context
import com.example.fofocaoficial.model.Article
import com.example.fofocaoficial.model.db.ArticleDatabase
import com.example.fofocaoficial.network.RetrofitInstance
import com.example.fofocaoficial.presenter.favorite.FavoriteHome
import com.example.fofocaoficial.presenter.news.NewsHome
import com.example.fofocaoficial.presenter.search.SearchHome
import kotlinx.coroutines.*

class NewsDataSource(context: Context) {

    private var db: ArticleDatabase = ArticleDatabase(context)
    private var newsRepository: NewsRepository = NewsRepository(db)

    fun getBreakingNews(callback: NewsHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.getBreakingNews("br")
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

    fun searchNews(term: String, callback: SearchHome.Presenter) {
        GlobalScope.launch(Dispatchers.Main) {
            val response = RetrofitInstance.api.searchNews(term)
            if (response.isSuccessful) {
                response.body()?.let { newsResponse ->
                    callback.onSuccess(newsResponse)
                }
                callback.onComplete()
            } else {
                callback.onError(response.message())
                callback.onComplete()
            }
        }
    }

    fun saveArticle(article: Article) {
        GlobalScope.launch(Dispatchers.Main) {
            newsRepository.updateInsert(article)
        }
    }

    fun getAllArticle(callback: FavoriteHome.Presenter) {
        var allArticles: List<Article>
        CoroutineScope(Dispatchers.IO).launch {
            allArticles = newsRepository.getAll()

            withContext(Dispatchers.Main) {
                callback.onSuccess(allArticles)
            }
        }
    }

    fun deleteArticle(article: Article?) {
        GlobalScope.launch(Dispatchers.Main) {
            article?.let { articleSafe ->
                newsRepository.delete(articleSafe)

            }
        }
    }
}

//Aqui configuramos o callback e criamos a coroutines e chamamos a classe que tem o retrofit, após isso listamos as noticias em MainActivity

// configuramos o menu e após isso seguimos para criar o UtilQuerytextlistener em Util, jutamente com Constants