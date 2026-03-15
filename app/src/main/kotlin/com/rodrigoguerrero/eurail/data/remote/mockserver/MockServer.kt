package com.rodrigoguerrero.eurail.data.remote.mockserver

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode

internal val mockEngine = MockEngine { request ->
    when (request.url.encodedPath) {
        "/articles" -> validResponse()
        "/articles/1" -> successDetailsResponse(articleOneDetails)
        "/articles/2" -> clientError()
        "/articles/3" -> serverError()
        "/articles/4" -> successDetailsResponse(articleFourDetails)
        "/articles/5" -> successDetailsResponse(articleFiveDetails)
        "/articles/6" -> successDetailsResponse(articleSixDetails)
        "/articles/7" -> successDetailsResponse(articleSevenDetails)
        "/articles/8" -> successDetailsResponse(articleEightDetails)
        "/articles/9" -> successDetailsResponse(articleNineDetails)
        "/articles/10" -> successDetailsResponse(articleTenDetails)
        "/articles/11" -> successDetailsResponse(articleElevenDetails)
        "/articles/12" -> successDetailsResponse(articleTwelveDetails)
        else -> respond(
            "Not Found",
            HttpStatusCode.NotFound
        )
    }
}
