package com.example.fofocaoficial.adapter

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.fofocaoficial.R
import com.example.fofocaoficial.model.Article
import kotlinx.android.synthetic.main.item_news.view.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainAdapter : Adapter<MainAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    var differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        )

    override fun getItemCount(): Int = differ.currentList.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvTitle.text = article.title
            tvPublishedAt.text = getDateRelease(article.publishedAt)

            setOnClickListener {
                onItemClickListener?.let { click ->
                    click(article)
                }
            }
        }
    }

    private var onItemClickListener: ((Article) -> Unit)? = null
    fun setOnClickListener(Listener: (Article) -> Unit) {
        onItemClickListener = Listener
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateRelease(date: String?) : String {
        return if (date != null && date != "") {
            val secondApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val timestamp =
                (SimpleDateFormat("yyyy-MM-dd").parse(date)?.time ?: 0) / 1000// timestamp in Long
            val timestampAsDateString = DateTimeFormatter.ISO_INSTANT
                .format(java.time.Instant.ofEpochSecond(timestamp))
            val dateTime = LocalDate.parse(timestampAsDateString, secondApiFormat)

            ""+dateTime.dayOfMonth+"/"+dateTime.monthValue+"/" +
                    ""+dateTime.year
        } else{
            ""
        }
    }
}

/*após configurar o adapter, seguimos para modelagem da arquitetura mvp, criamos o presenter News presenter e search presenter, view home e view e favorite  */