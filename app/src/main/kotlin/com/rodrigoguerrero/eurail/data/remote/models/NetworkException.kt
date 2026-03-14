package com.rodrigoguerrero.eurail.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
sealed class NetworkException : Exception() {
    class ServerError : NetworkException()
    @Serializable
    data class ClientError(
        val errorCode: Int? = null,
        val errorTitle: String? = null,
        val errorMessage: String? = null,
    ) : NetworkException()
    class ParsingError : NetworkException()
    class UnknownError : NetworkException()
}
