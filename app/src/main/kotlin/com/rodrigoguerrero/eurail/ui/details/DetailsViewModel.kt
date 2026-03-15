package com.rodrigoguerrero.eurail.ui.details

import androidx.lifecycle.SavedStateHandle
import com.rodrigoguerrero.eurail.ui.mvi.Middleware
import com.rodrigoguerrero.eurail.ui.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class DetailsViewModel @Inject constructor(
    middlewares: Set<@JvmSuppressWildcards Middleware<DetailsState, DetailsAction>>,
    reducer: DetailsReducer,
    savedStateHandle: SavedStateHandle,
) : MviViewModel<DetailsState, DetailsAction>(
    middlewares = middlewares.toList(),
    reducer = reducer,
    initialState = DetailsState.initial,
) {
    init {
        val id = savedStateHandle.get<Int>("id")
        dispatch(DetailsAction.OnLoad(id))
    }
}
