package com.rodrigoguerrero.eurail.ui.di

import com.rodrigoguerrero.eurail.domain.articles.interactors.ArticlesInteractor
import com.rodrigoguerrero.eurail.domain.articles.interactors.ArticlesInteractorImpl
import com.rodrigoguerrero.eurail.ui.main.MainAction
import com.rodrigoguerrero.eurail.ui.main.MainMiddleware
import com.rodrigoguerrero.eurail.ui.main.MainNetworkMiddleware
import com.rodrigoguerrero.eurail.ui.main.MainState
import com.rodrigoguerrero.eurail.ui.mvi.Middleware
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
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
}
