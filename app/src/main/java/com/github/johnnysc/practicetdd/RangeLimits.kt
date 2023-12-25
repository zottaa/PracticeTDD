package com.github.johnnysc.practicetdd

import android.util.Range

interface RangeLimits {

    fun pair(number: Int): RangePair

    class Base(private val list: List<Int>) : RangeLimits {

        override fun pair(number: Int): RangePair {

            if (list.isEmpty()) {
                return RangePair(Int.MIN_VALUE, Int.MAX_VALUE)
            }

            val possibleIndex = findPlace(number)


            if (possibleIndex == list.size) {
                return RangePair(list[list.size - 1], Int.MAX_VALUE)
            } else if (possibleIndex == 0) {
                return if (list[possibleIndex] == number) {
                    if (list.size > 1) {
                        RangePair(Int.MIN_VALUE, list[1])
                    } else {
                        RangePair(Int.MIN_VALUE, Int.MAX_VALUE)
                    }
                } else {
                    RangePair(Int.MIN_VALUE, list[0])
                }
            } else {
                return if (list[possibleIndex] == number) {
                    if (possibleIndex + 1 != list.size) {
                        RangePair(list[possibleIndex - 1], list[possibleIndex + 1])
                    } else {
                        RangePair(list[possibleIndex - 1], Int.MAX_VALUE)
                    }
                } else {
                    RangePair(list[possibleIndex - 1], list[possibleIndex])
                }
            }
        }

        private fun findPlace(number: Int): Int {
            var left = 0
            var right = list.size - 1

            while (left <= right) {
                val mid = (left + right) / 2
                val midValue = list[mid]

                if (midValue == number) {
                    return mid
                } else if (midValue < number) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }

            return left
        }
    }
}