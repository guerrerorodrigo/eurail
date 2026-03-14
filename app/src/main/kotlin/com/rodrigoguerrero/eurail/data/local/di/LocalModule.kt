package com.rodrigoguerrero.eurail.data.local.di

import android.content.Context
import com.rodrigoguerrero.eurail.data.remote.config.createHttpClient
import com.rodrigoguerrero.eurail.data.remote.mockserver.mockEngine
import com.rodrigoguerrero.shared.data.local.AppDatabase
import com.rodrigoguerrero.shared.data.local.getDatabaseBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LocalModule {

    @Singleton
    @Provides
    fun provideHttpClient(
        @ApplicationContext context: Context,
    ): AppDatabase = getDatabaseBuilder(context).build()
}
