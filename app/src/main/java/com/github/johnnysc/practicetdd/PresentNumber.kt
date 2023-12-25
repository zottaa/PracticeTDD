package com.github.johnnysc.practicetdd

import java.math.BigDecimal

interface PresentNumber {

    fun number(): Number

    class Base(private val raw: String) : PresentNumber {

        private var intPart: MutableList<Int> = mutableListOf<Int>()
        private var floatPart: MutableList<Int> = mutableListOf<Int>()
        private var isNegative = false

        init {

            var pointer = intPart

            raw.forEach { c ->
                when (c) {
                    '0' -> pointer.add(0)
                    '1' -> pointer.add(1)
                    '2' -> pointer.add(2)
                    '3' -> pointer.add(3)
                    '4' -> pointer.add(4)
                    '5' -> pointer.add(5)
                    '6' -> pointer.add(6)
                    '7' -> pointer.add(7)
                    '8' -> pointer.add(8)
                    '9' -> pointer.add(9)
                    '.' -> pointer = floatPart
                    'f' -> pointer = floatPart
                    '-' -> isNegative = true
                }
            }
        }


        override fun number(): Number {
            return if (floatPart.isNotEmpty() || intPart.size >= 19) {
                handleFloating()
            } else {
                handleInteger()
            }
        }

        private fun handleFloating() : Number {
            var result = BigDecimal.ZERO

            intPart.reversed().forEachIndexed { index, digit ->
                result = result.plus(BigDecimal(digit).multiply(BigDecimal(10).pow(index)))
            }

            floatPart.forEachIndexed { index, digit ->
                result = result.plus(BigDecimal(digit).divide(BigDecimal(10).pow(index + 1)))
            }

            if (isNegative) {
                result = result.negate()
            }

            if (
                BigDecimal((Float.MAX_VALUE * -1).toString()) <= result
                &&
                result <= BigDecimal(Float.MAX_VALUE.toString())
            ) {
                return result.toFloat()
            }

            return result.toDouble()
        }

        private fun handleInteger() : Number {
            var result = 0L
            intPart.forEach { digit ->
                result = result * 10 + digit
            }

            if (isNegative) {
                result *= -1
            }

            if (Byte.MIN_VALUE <= result && result <= Byte.MAX_VALUE) {
                return result.toByte()
            }
            if (Short.MIN_VALUE <= result && result <= Short.MAX_VALUE) {
                return result.toShort()
            }
            if (Int.MIN_VALUE <= result && result <= Int.MAX_VALUE) {
                return result.toInt()
            }

            return result
        }
    }
}