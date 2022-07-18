package com.example.fofocaoficial.ui


import android.app.ProgressDialog.show
import android.content.Intent
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fofocaoficial.R
import com.example.fofocaoficial.adapter.MainAdapter
import com.example.fofocaoficial.model.Article
import com.example.fofocaoficial.model.data.NewsDataSource
import com.example.fofocaoficial.presenter.ViewHome
import com.example.fofocaoficial.presenter.favorite.FavoritePresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AbstractActivity(), ViewHome.Favorite {
    private val mainAdapter by lazy {
        MainAdapter()
    }

    private lateinit var presenter: FavoritePresenter

    override fun getLayout(): Int = R.layout.activity_favorite

    override fun onInject() {

        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)
        presenter.getAll()
        configRecycle()
        clickAdapter()

        val itemTouchPerCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = mainAdapter.differ.currentList[position]
                presenter.deleteArticle(article)
                Snackbar.make(
                    viewHolder.itemView, R.string.article_delete_successful,
                    Snackbar.LENGTH_SHORT
                ).apply {
                    setAction(getString(R.string.undo)) {
                        presenter.saveArticle(article)
                        mainAdapter.notifyDataSetChanged()

                    }
                    show()
                }

            }

        }
        ItemTouchHelper(itemTouchPerCallback).apply {
            attachToRecyclerView(rvFavorite)
        }
        presenter.getAll()
    }

    private fun configRecycle() {
        with(rvFavorite) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@FavoriteActivity, DividerItemDecoration.VERTICAL
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

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }
}

//Fizemos o mesmo que no anterior chamando o construtor e importando os membros, após isso fomos para o newsDataSource para buscar noticias.

//Configuramos o Favorite Presenter e Newsdata source  e fomos para funcao de deletar noticias dos favoritos  e finalizamos