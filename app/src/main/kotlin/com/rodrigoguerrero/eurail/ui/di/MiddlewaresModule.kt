package com.rodrigoguerrero.eurail.ui.di

import com.rodrigoguerrero.eurail.ui.details.DetailsAction
import com.rodrigoguerrero.eurail.ui.details.DetailsMiddleware
import com.rodrigoguerrero.eurail.ui.details.DetailsState
import com.rodrigoguerrero.eurail.ui.main.MainAction
import com.rodrigoguerrero.eurail.ui.main.MainMiddleware
import com.rodrigoguerrero.eurail.ui.main.MainNetworkMiddleware
import com.rodrigoguerrero.eurail.ui.main.MainState
import com.rodrigoguerrero.eurail.ui.mvi.Middleware
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ViewModelComponent::class)
internal interface MiddlewaresModule {
    @Binds
    @IntoSet
    fun bindMainMiddleware(
        middleware: MainMiddleware,
    ): Middleware<MainState, MainAction>

    @Binds
    @IntoSet
    fun bindMainNetworkMiddleware(
        middleware: MainNetworkMiddleware,
    ): Middleware<MainState, MainAction>

    @Binds
    @IntoSet
    fun bindDetailsMiddleware(
        middleware: DetailsMiddleware,
    ): Middleware<DetailsState, DetailsAction>
}
