package com.github.johnnysc.practicetdd


interface Numbers {

    fun minimum(): Int

    fun maximum(): Int

    class Base(
        private val first: Int,
        private val second: Int
    ) : Numbers {
        private var max: Int = Int.MAX_VALUE
        private var min: Int = Int.MIN_VALUE
        init {
            if (first >= second) {
                max = first
                min = second
            } else {
                max = second
                min = first
            }
        }


        override fun minimum(): Int = min

        override fun maximum(): Int = max
    }

    class List(
        private val list: kotlin.collections.List<Int>
    ) : Numbers {
        private var max: Int = Int.MAX_VALUE
        private var min: Int = Int.MIN_VALUE

        init {
            if (list.isNotEmpty()) {
                max = list[0]
                min = list[0]
                list.forEach {
                    if (it > max) {
                        max = it
                    }
                    if (it < min) {
                        min = it
                    }
                }
            }
        }
        override fun minimum(): Int = min

        override fun maximum(): Int = max
    }


}