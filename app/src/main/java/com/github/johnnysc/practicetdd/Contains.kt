package com.github.johnnysc.practicetdd

interface Contains {

    fun contains(collection: List<String>, item: String) : Boolean

    class Base(private val forWrapper: For) : Contains {

        override fun contains(collection: List<String>, item: String): Boolean {
            var result = false
            forWrapper.repeat(collection.size, 0) { index ->
                result = collection[index] == item
                result
            }
            return result
        }

    }
}