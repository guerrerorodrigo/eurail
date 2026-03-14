package com.rodrigoguerrero.eurail.ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoguerrero.eurail.ui.mvi.Middleware
import com.rodrigoguerrero.eurail.ui.mvi.MviAction
import kotlinx.coroutines.flow.StateFlow

open class MviViewModel<S : State, A : MviAction>(
    initialState: S,
    reducer: Reducer<S, A>,
    middlewares: List<Middleware<S, A>>,
) : ViewModel() {

    private val mviOrchestrator = MviOrchestrator(
        initialState = initialState,
        scope = viewModelScope,
        reducer = reducer,
        middlewares = middlewares,
    )

    val state: StateFlow<S> = mviOrchestrator

    init {
        middlewares.forEach { middleware -> middleware.setup(viewModelScope, mviOrchestrator) }
    }

    fun dispatch(action: A) {
        mviOrchestrator.dispatch(action)
    }
}
