package com.rodrigoguerrero.eurail.ui.mvi

import kotlinx.coroutines.CoroutineScope

abstract class Middleware<S: State, A: MviAction> {
    protected lateinit var scope: CoroutineScope
    private lateinit var dispatcher: MviDispatcher<A>

    internal fun setup(scope: CoroutineScope, dispatcher: MviDispatcher<A>) {
        this.scope = scope
        this.dispatcher = dispatcher
    }

    abstract fun process(state: S, action: A)

    protected fun dispatch(action: A) = dispatcher.dispatch(action)
}
