package com.rodrigoguerrero.eurail.ui.common.components

import androidx.annotation.StringRes
import com.rodrigoguerrero.eurail.R
import com.rodrigoguerrero.eurail.data.remote.models.NetworkException
import com.rodrigoguerrero.eurail.utils.network.NoNetworkException

sealed interface FullScreenMessageState {
    val ctaLabelRes: Int

    data class LocalFullScreenMessage(
        @StringRes val messageRes: Int,
        @StringRes override val ctaLabelRes: Int,
    ) : FullScreenMessageState

    data class RemoteFullScreenMessage(
        val messageRes: String,
        @StringRes override val ctaLabelRes: Int,
    ) : FullScreenMessageState
}

fun createFullScreenMessageState(
    throwable: Throwable,
) = when (throwable) {
    is NetworkException.ClientError -> buildClientError(throwable)

    is NoNetworkException -> FullScreenMessageState.LocalFullScreenMessage(
        messageRes = R.string.there_is_no_internet_connection,
        ctaLabelRes = R.string.try_again,
    )

    else -> FullScreenMessageState.LocalFullScreenMessage(
        messageRes = R.string.generic_error,
        ctaLabelRes = R.string.try_again,
    )
}

fun buildClientError(throwable: NetworkException.ClientError): FullScreenMessageState {
    return throwable.errorMessage?.let {
        FullScreenMessageState.RemoteFullScreenMessage(
            messageRes = throwable.errorMessage,
            ctaLabelRes = R.string.try_again,
        )
    } ?: FullScreenMessageState.LocalFullScreenMessage(
        messageRes = R.string.details_error,
        ctaLabelRes = R.string.try_again,
    )
}
