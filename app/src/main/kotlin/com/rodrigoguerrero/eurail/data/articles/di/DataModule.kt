package com.rodrigoguerrero.eurail.data.articles.di

import com.rodrigoguerrero.eurail.data.articles.datasources.ArticlesLocalDataSource
import com.rodrigoguerrero.eurail.data.articles.datasources.ArticlesLocalDataSourceImpl
import com.rodrigoguerrero.eurail.data.articles.datasources.ArticlesRemoteDataSource
import com.rodrigoguerrero.eurail.data.articles.datasources.ArticlesRemoteDataSourceImpl
import com.rodrigoguerrero.eurail.data.articles.repositories.ArticlesRepository
import com.rodrigoguerrero.eurail.data.articles.repositories.ArticlesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {
    @Binds
    fun bindRemoteDataSource(
        impl: ArticlesRemoteDataSourceImpl,
    ): ArticlesRemoteDataSource

    @Binds
    fun bindLocalDataSource(
        impl: ArticlesLocalDataSourceImpl,
    ): ArticlesLocalDataSource

    @Binds
    fun bindArticlesRepository(
        impl: ArticlesRepositoryImpl,
    ): ArticlesRepository
}
