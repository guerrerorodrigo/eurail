package com.rodrigoguerrero.eurail.data.remote.mockserver

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel

internal val mockEngine = MockEngine { request ->
    when (request.url.encodedPath) {
        "/articles" -> respond(
            content = ByteReadChannel(
                """
                    {
                      "articles": [
                        { "id": "1", "title": "First Article", "summary": "Summary", "updated_date": "31 May 2025" },
                        { "id": "2", "title": "Second Article", "summary": "Summary 2", "updated_date": "21 June 2025"  },
                        { "id": "3", "title": "Third Article", "summary": "Summary 3", "updated_date": "1 April 2025"  }
                      ]
                    }
                """.trimIndent()
            ),
            status = HttpStatusCode.OK,
            headers = headersOf(HttpHeaders.ContentType, "application/json"),
        )

        else -> respond(
            "Not Found",
            HttpStatusCode.NotFound
        )
    }
}
