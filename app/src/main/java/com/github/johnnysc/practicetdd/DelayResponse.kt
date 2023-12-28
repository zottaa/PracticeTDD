package com.github.johnnysc.practicetdd

import kotlinx.coroutines.delay

interface DelayResponse {

    suspend fun <T> delayAfter(delayInMillis: Long, block: suspend () -> T): T

    class Base(private val now: Now) : DelayResponse {

        override suspend fun <T> delayAfter(delayInMillis: Long, block: suspend () -> T): T {
            val start = now.time()
            val result = block.invoke()
            return if (now.time() - start == delayInMillis) {
                result
            } else {
                delay(delayInMillis - (now.time() - start))
                result
            }
        }
    }

}