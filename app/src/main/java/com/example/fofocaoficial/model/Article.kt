package com.example.fofocaoficial.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?,

    ) : Serializable
 //serializable serve para passar o objeto para outra activity, após isso configuramos o newsResponse


//configuramos o Entity e vamos para o articleDao