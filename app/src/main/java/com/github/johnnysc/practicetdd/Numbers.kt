package com.github.johnnysc.practicetdd

interface Numbers : Divide<Double>, Difference<Int> {

    fun isSumLong(): Boolean

    fun sumInt(): Int

    fun sumLong(): Long

    override fun difference(): Int

    override fun divide(): Double

    class Base(
        private val number1: Int,
        private val number2: Int
    ) : Numbers {

        private var isSumLong = false
        private var isChecked = false

        override fun isSumLong(): Boolean {
            isChecked = true
            isSumLong = if (number1 < 1) {
                number2 < Int.MIN_VALUE - number1
            } else {
                number2 > Int.MAX_VALUE - number1
            }
            return isSumLong
        }

        override fun sumInt(): Int {
            if (isChecked.not()) {
                throw IllegalAccessException()
            }
            if (isSumLong) {
                throw IllegalStateException()
            } else {
                return number1 + number2
            }
        }

        override fun sumLong(): Long {
            if (isChecked.not()) {
                throw IllegalAccessException()
            }
            if (isSumLong) {
                return number1.toLong() + number2.toLong()
            } else {
                throw IllegalStateException()
            }
        }

        override fun difference(): Int =
            number1 - number2

        override fun divide(): Double {
            if (number2 == 0) {
                throw IllegalArgumentException()
            }
            return number1.toDouble() / number2
        }

    }

}



