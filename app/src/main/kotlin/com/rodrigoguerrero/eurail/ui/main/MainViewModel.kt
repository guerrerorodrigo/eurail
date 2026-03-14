package com.rodrigoguerrero.eurail.ui.main

import com.rodrigoguerrero.eurail.ui.mvi.Middleware
import com.rodrigoguerrero.eurail.ui.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    middlewares: Set<@JvmSuppressWildcards Middleware<MainState, MainAction>>,
    reducer: MainReducer,
) : MviViewModel<MainState, MainAction>(
    middlewares = middlewares.toList(),
    reducer = reducer,
    initialState = MainState.initial,
)
