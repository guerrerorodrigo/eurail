package com.rodrigoguerrero.eurail.data.remote.mockserver

import io.ktor.client.engine.mock.MockRequestHandleScope
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel

internal fun MockRequestHandleScope.validResponse() = getResponse(
    response = validResponseText,
    statusCode = HttpStatusCode.OK,
)

internal fun MockRequestHandleScope.validEmptyResponse() = getResponse(
    response = validEmptyResponseText,
    statusCode = HttpStatusCode.OK,
)

internal fun MockRequestHandleScope.invalidJsonResponse() = getResponse(
    response = invalidJsonResponseText,
    statusCode = HttpStatusCode.OK,
)

internal fun MockRequestHandleScope.clientError() = getResponse(
    response = clientErrorResponse,
    statusCode = HttpStatusCode.Unauthorized,
)

internal fun MockRequestHandleScope.serverError() = getResponse(
    response = "",
    statusCode = HttpStatusCode.BadGateway,
)

private fun MockRequestHandleScope.getResponse(
    response: String,
    statusCode: HttpStatusCode,
) = respond(
    content = ByteReadChannel(response),
    status = statusCode,
    headers = headersOf(HttpHeaders.ContentType, "application/json"),
)

internal fun MockRequestHandleScope.successDetailsResponse() = respond(
    content = ByteReadChannel(
        """
                    {
                      "id": 1,
                      "title": "Article 1",
                      "content": "# Sample\n* Markdown\n* [Link](https://example.com)\n![Image](https://example.com/img.png)\n<a href=\"https://www.google.com/\">Google</a>\n\n## Gif Images\n![Image](https://user-images.githubusercontent.com/14011726/94132137-7d4fc100-fe7c-11ea-8512-69f90cb65e48.gif)\n\n## Checks\n- [x] Review #123\n- [ ] Complete XYZ\n- [ ] Add delight to the experience when all tasks are complete :tada:\n\n## Text highlight\nSometimes we need to ==highlight== a text in `different` ways.\n<h2>Head with heading break color blue</h2>\n <h2>Another text here</h2>\n <h2>Another text here</h2>\n <h2>Another text here</h2>\n <h2>Another text here</h2>\n <h2>Another text here</h2>\n <h2>Another text here</h2>\n <h2>Another text here</h2>\n <h2>Another text here</h2>\n <h2>Another text here</h2>\n <h2>Another text here</h2>",
                      "updated_date": "31 May 2025"
                    }
                """.trimIndent()
    ),
    status = HttpStatusCode.OK,
    headers = headersOf(HttpHeaders.ContentType, "application/json"),
)
