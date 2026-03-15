package com.rodrigoguerrero.eurail.data.articles.datasources

import com.rodrigoguerrero.eurail.data.remote.models.ArticleDetailsDto
import com.rodrigoguerrero.eurail.data.remote.models.ArticlesDto
import com.rodrigoguerrero.eurail.data.remote.requests.ArticleDetailsRequest
import com.rodrigoguerrero.eurail.data.remote.requests.ArticlesRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.resources.get
import javax.inject.Inject

internal class ArticlesRemoteDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : ArticlesRemoteDataSource {
    override suspend fun getArticles(): Result<ArticlesDto> {
        return runCatching {
            httpClient
                .get(resource = ArticlesRequest)
                .body<ArticlesDto>()
        }
    }

    override suspend fun getArticleDetails(id: Int): Result<ArticleDetailsDto> {
        return runCatching {
            httpClient
                .get(resource = ArticleDetailsRequest.Id(id = id))
                .body()
        }
    }
}
