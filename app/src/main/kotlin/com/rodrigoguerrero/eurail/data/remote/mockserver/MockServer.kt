package com.rodrigoguerrero.eurail.data.remote.mockserver

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode

internal val mockEngine = MockEngine { request ->
    when (request.url.encodedPath) {
        "/articles" -> validResponse()
        "/articles/1" -> successDetailsResponse()
        "/articles/2" -> clientError()
        "/articles/3" -> serverError()
        else -> respond(
            "Not Found",
            HttpStatusCode.NotFound
        )
    }
}
