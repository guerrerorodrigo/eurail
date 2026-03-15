package com.rodrigoguerrero.shared.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.rodrigoguerrero.shared.data.local.dao.ArticleDao
import com.rodrigoguerrero.shared.data.local.entities.ArticleDetailsEntity
import com.rodrigoguerrero.shared.data.local.entities.ArticleEntity

@Database(
    entities = [ArticleEntity::class, ArticleDetailsEntity::class],
    version = 1,
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticleDao
}

expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}
