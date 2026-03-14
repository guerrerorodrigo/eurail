package com.rodrigoguerrero.eurail.data.remote.di

import com.rodrigoguerrero.eurail.data.remote.config.createHttpClient
import com.rodrigoguerrero.eurail.data.remote.mockserver.mockEngine
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return createHttpClient(httpClientEngine = mockEngine)
    }
}
