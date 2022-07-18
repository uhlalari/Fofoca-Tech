package com.example.fofocaoficial.ui

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fofocaoficial.R
import com.example.fofocaoficial.adapter.MainAdapter
import com.example.fofocaoficial.model.Article
import com.example.fofocaoficial.model.data.NewsDataSource
import com.example.fofocaoficial.presenter.ViewHome
import com.example.fofocaoficial.presenter.search.SearchPresenter
import com.example.fofocaoficial.util.UtilQueryTextListener
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AbstractActivity(), ViewHome.View {
    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: SearchPresenter
    override fun getLayout(): Int = R.layout.activity_search

    override fun onInject() {

        val dataSource = NewsDataSource(this)
        presenter = SearchPresenter(this, dataSource)
        configRecycle()
        search()
        clickAdapter()
    }

    private fun search() {
        searchNews.setOnQueryTextListener(
            UtilQueryTextListener(
                this.lifecycle
            ) { newText ->
                newText?.let { query ->
                    if (query.isNotEmpty()) {
                        presenter.search(query)
                        rvProgressBarSearch.visibility = View.VISIBLE

                    }
                }
            }
        )
    }

    private fun configRecycle() {
        with(rvSearch) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@SearchActivity, DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    private fun clickAdapter() {
        mainAdapter.setOnClickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }

    override fun showProgressBar() {
        rvProgressBarSearch.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        rvProgressBarSearch.visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }
}

//Configuramos o activity_search - layout e configuramos o searchPresenter