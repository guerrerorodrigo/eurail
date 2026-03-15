package com.rodrigoguerrero.eurail.data.articles.repositories

import com.rodrigoguerrero.eurail.data.articles.models.Article
import com.rodrigoguerrero.eurail.data.articles.models.ArticleDetails

interface ArticlesRepository {
    /**
     * Returns the list of articles. It is a cache first strategy.
     * If there are no valid cache articles, it retrieves the articles from the BE
     * This clears the cache and saves the newly retrieve articles
     */
    suspend fun getArticles(): Result<List<Article>>

    /**
     * Retrieves the remote articles.
     * This method clears the cached articles in order to save the newly retrieved articles
     */
    suspend fun getRemoteArticles(): Result<List<Article>>
    suspend fun getArticleDetails(id: Int): Result<ArticleDetails>
}
