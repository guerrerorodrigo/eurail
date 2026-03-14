package com.rodrigoguerrero.shared.data.local

import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
internal object CachePolicy {
    private val ttl: Duration = 24.hours

    fun isValid(cachedAt: Long): Boolean {
        val now = Clock.System.now().toEpochMilliseconds()
        return now - cachedAt < ttl.inWholeMilliseconds
    }
}
