package com.example.fofocaoficial.model.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fofocaoficial.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {

        @Volatile
        private var instance: ArticleDatabase? = null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: createdDatabase(context).also { articleDatabase ->
                instance = articleDatabase
            }
        }

        private fun createdDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            )
                .allowMainThreadQueries()
                .build()
    }
}

//configuramos e fomos para source em db

//posteriormente configuramos o repositorio em repository, indo para news repository