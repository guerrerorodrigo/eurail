package com.rodrigoguerrero.eurail.ui.mvi

interface Reducer<S: State, A: MviAction> {
    fun reduce(state: S, action: A): S
}
