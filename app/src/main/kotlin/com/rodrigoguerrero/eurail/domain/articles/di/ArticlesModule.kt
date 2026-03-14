package com.rodrigoguerrero.eurail.domain.articles.di

import com.rodrigoguerrero.eurail.domain.articles.interactors.ArticlesInteractor
import com.rodrigoguerrero.eurail.domain.articles.interactors.ArticlesInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal interface ArticlesModule {
    @Binds
    @ViewModelScoped
    fun bindArticlesInteractorImpl(
        impl: ArticlesInteractorImpl,
    ): ArticlesInteractor
}
