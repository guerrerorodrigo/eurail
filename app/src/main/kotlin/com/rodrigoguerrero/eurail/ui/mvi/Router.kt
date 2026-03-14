package com.rodrigoguerrero.eurail.ui.mvi

interface Router<A : MviAction> {
    fun process(action: A)
}
