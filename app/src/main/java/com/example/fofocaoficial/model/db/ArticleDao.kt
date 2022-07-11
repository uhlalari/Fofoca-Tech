package com.example.fofocaoficial.model.db

import androidx.room.*
import androidx.room.Dao
import androidx.room.Insert
import com.example.fofocaoficial.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateInsert(article: Article)

    @Query("SELECT * FROM articles")
    fun getAll(): List<Article>

    @Delete
    fun delete(article: Article)
}