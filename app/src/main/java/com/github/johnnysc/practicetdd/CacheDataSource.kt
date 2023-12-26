package com.github.johnnysc.practicetdd

import android.os.Build
import androidx.annotation.RequiresApi

interface CacheDataSource {

    fun add(item: SimpleData)

    fun data(): List<SimpleData>

    class Timed(
        private val now: Now,
        private val lifeTimeMillis: Long
    ) : CacheDataSource {

        private val data = mutableMapOf<SimpleData, Long>()

        override fun add(item: SimpleData) {
            data[item] = now.now()
        }

        override fun data(): List<SimpleData> {

            val currentTime = now.now()

            data.values.removeIf { addTime ->
                (lifeTimeMillis < (currentTime - addTime))
            }

            return data.keys.toList()
        }

    }
}