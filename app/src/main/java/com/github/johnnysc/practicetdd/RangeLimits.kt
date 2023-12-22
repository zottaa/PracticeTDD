package com.github.johnnysc.practicetdd

interface RangeLimits {

    fun pair(number: Int): RangePair

    class Base(private val list: List<Int>) : RangeLimits {

        override fun pair(number: Int): RangePair {

            var left = Int.MIN_VALUE
            var right = Int.MAX_VALUE

            if (list.isEmpty()) {
                return RangePair(left, right)
            }

            val tempList: MutableList<Int> = list.toMutableList()
            if (!tempList.contains(number)) {
                tempList.add(number)
                tempList.sort()
            }

            val index = tempList.binarySearch(number)

            if (index != 0) {
                if (index != (tempList.size - 1)) {
                    left = tempList[index - 1]
                    right = tempList[index + 1]
                } else {
                    left = tempList[index - 1]
                }
            } else if (index != (tempList.size - 1)) {
                right = tempList[index + 1]
            }
            return RangePair(left, right)
        }

    }
}

data class RangePair(private val left: Int, private val right: Int)