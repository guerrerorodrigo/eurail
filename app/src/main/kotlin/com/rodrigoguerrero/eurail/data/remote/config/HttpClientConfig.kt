package com.rodrigoguerrero.eurail.data.remote.config

import com.rodrigoguerrero.eurail.data.remote.models.NetworkException
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.Resources
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol
import io.ktor.serialization.JsonConvertException
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(httpClientEngine: HttpClientEngine) = HttpClient(httpClientEngine) {
    expectSuccess = true
    install(Resources)
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }

    exceptionHandling()

    defaultRequest {
        url {
            host = "api.articles.com"
            protocol = URLProtocol.HTTPS
        }
    }
}

private fun <T : HttpClientEngineConfig> HttpClientConfig<T>.exceptionHandling() {
    HttpResponseValidator {
        handleResponseExceptionWithRequest { exception, _ ->
            val networkException = when (exception) {
                is ClientRequestException -> exception.getClientError()
                is ServerResponseException -> NetworkException.ServerError()
                is JsonConvertException -> NetworkException.ParsingError()
                else -> NetworkException.UnknownError()
            }
            throw networkException
        }
    }
}

private suspend fun ClientRequestException.getClientError(): NetworkException.ClientError {
    val errorBody = response.bodyAsText()
    val error = try {
        Json.decodeFromString<NetworkException.ClientError>(errorBody)
    } catch (e: Exception) {
        NetworkException.ClientError()
    }
    return error
}
