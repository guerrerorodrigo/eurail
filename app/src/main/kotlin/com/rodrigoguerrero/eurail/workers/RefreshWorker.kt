package com.rodrigoguerrero.eurail.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.rodrigoguerrero.eurail.data.articles.repositories.ArticlesRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class RefreshWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: ArticlesRepository,
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        repository
            .getRemoteArticles()
            .fold(
                onSuccess = { return Result.success() },
                onFailure = { return Result.failure() },
            )
    }
}
