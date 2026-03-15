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

internal fun MockRequestHandleScope.successDetailsResponse(
    detailsResponse: String,
) = respond(
    content = ByteReadChannel(detailsResponse),
    status = HttpStatusCode.OK,
    headers = headersOf(HttpHeaders.ContentType, "application/json"),
)
