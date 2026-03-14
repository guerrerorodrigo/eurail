package com.rodrigoguerrero.eurail.ui.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalForInheritanceCoroutinesApi::class)
internal class MviOrchestrator<S : State, A : MviAction>(
    initialState: S,
    private val reducer: Reducer<S, A>,
    private val middlewares: List<Middleware<S, A>>,
    private val scope: CoroutineScope,
    private val router: Router<A>? = null,
) : StateFlow<S>, MviDispatcher<A> {

    private val actions = MutableSharedFlow<A>(extraBufferCapacity = 64)
    private val stateFlow: StateFlow<S> = actions
        .runningFold(
            initial = initialState,
            operation = ::reduce,
        )
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly,
            initialValue = initialState,
        )

    override val replayCache: List<S> get() = stateFlow.replayCache

    override val value: S get() = stateFlow.value

    override suspend fun collect(collector: FlowCollector<S>): Nothing {
        stateFlow.collect(collector)
    }

    override fun dispatch(action: A) {
        scope.launch { actions.emit(action) }
    }

    private fun reduce(state: S, action: A): S {
        router?.process(action)
        middlewares.forEach { middleware -> middleware.process(state, action) }
        return reducer.reduce(state, action)
    }
}
