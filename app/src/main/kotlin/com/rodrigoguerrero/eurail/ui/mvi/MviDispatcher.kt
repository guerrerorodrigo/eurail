package com.rodrigoguerrero.eurail.ui.mvi

interface MviDispatcher<A : MviAction> {
    fun dispatch(action: A)
}
