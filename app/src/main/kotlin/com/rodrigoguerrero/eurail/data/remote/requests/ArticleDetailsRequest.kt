package com.rodrigoguerrero.eurail.data.remote.requests

import io.ktor.resources.Resource

@Resource("/articles")
class ArticleDetailsRequest {
    @Resource("{id}")
    class Id(val parent: ArticleDetailsRequest = ArticleDetailsRequest(), val id: Int)
}
