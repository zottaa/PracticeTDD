package com.github.johnnysc.practicetdd

interface Sorting {

    fun sort(list: List<Int>): List<Int>

    class Base(
        private val forOut: For,
        private val forIn: For
    ) : Sorting {

        override fun sort(list: List<Int>): List<Int> {
            val sortedList = list.toMutableList()
            forOut.repeat(sortedList.size) { i ->
                var swapped = false
                forIn.repeat(sortedList.size - i - 1) { j ->
                    if (sortedList[j] > sortedList[j + 1]) {
                        sortedList[j] = sortedList[j + 1].also {
                            sortedList[j + 1] = sortedList[j]
                        }
                        swapped = true
                    }
                    false
                }
                !swapped
            }
            return sortedList
        }

    }
}