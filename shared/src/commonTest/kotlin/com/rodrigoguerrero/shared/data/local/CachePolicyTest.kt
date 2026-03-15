package com.rodrigoguerrero.shared.data.local

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.time.Clock
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
internal class CachePolicyTest {
    private val clock = FakeClock(Instant.parse("2026-03-15T10:00:00Z"))
    private val subject = CachePolicy(clock = clock)

    @Test
    fun cachedAtIsValidWhenLessThan24HoursHavePassed() {
        val lastUpdated = clock.now().toEpochMilliseconds()
        clock.advance(23.hours.inWholeSeconds)
        val isValid = subject.isValid(lastUpdated)

        assertTrue(isValid)
    }

    @Test
    fun cachedAtIsNotValidWhen24HoursHavePassed() {
        val lastUpdated = clock.now().toEpochMilliseconds()
        clock.advance(24.hours.inWholeSeconds)
        val isValid = subject.isValid(lastUpdated)

        assertFalse(isValid)
    }
}

@OptIn(ExperimentalTime::class)
internal class FakeClock(
    private var instant: Instant
) : Clock {

    override fun now(): Instant = instant

    fun advance(seconds: Long) {
        instant += seconds.seconds
    }
}
