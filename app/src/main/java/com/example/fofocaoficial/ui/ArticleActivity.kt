package com.example.fofocaoficial.ui

import android.webkit.WebViewClient
import com.example.fofocaoficial.R
import com.example.fofocaoficial.model.Article
import com.example.fofocaoficial.model.data.NewsDataSource
import com.example.fofocaoficial.presenter.ViewHome
import com.example.fofocaoficial.presenter.favorite.FavoritePresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AbstractActivity(), ViewHome.Favorite {
    private lateinit var article: Article
    private lateinit var presenter: FavoritePresenter

    override fun getLayout(): Int = R.layout.activity_article

    override fun onInject() {
        getArticle()
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)

        webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { url ->
                loadUrl(url)
            }
        }

        fab.setOnClickListener {
            presenter.saveArticle(article)
            Snackbar.make(
                it, R.string.article_saved_successful,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun getArticle() {
        intent.extras?.let { articleSend ->
            article = articleSend.get("article") as Article
        }
    }

    override fun showArticles(articles: List<Article>) {}
}

//Pegamos o contrutor e implementamos os métodos necessarios e fomos para o favoriteActivity para configuracao dele


//Aqui criamos o webview e vamos para a main activity

//finalizamos a configuracao do intent e após isso vamos para o setup Room em Article em db

// fomos para listagem das noticias favoritas em FavorityActivity