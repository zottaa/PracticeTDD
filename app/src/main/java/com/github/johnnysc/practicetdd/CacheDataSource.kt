package com.github.johnnysc.practicetdd

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

            val list = mutableListOf<SimpleData>()
            val removeList = mutableListOf<SimpleData>()

            data.forEach { (item, time) ->
                if (lifeTimeMillis < (now.now() - time)) {
                    removeList.add(item)
                } else {
                    list.add(item)
                }
            }

            removeList.forEach {
                data.remove(it)
            }

            return list
        }

    }
}