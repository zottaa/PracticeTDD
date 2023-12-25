package com.github.johnnysc.practicetdd

import java.lang.IllegalStateException
import java.math.BigDecimal
import kotlin.math.pow

interface Parser {

    fun parse(raw: String): List<Any>

    class Base(private val delimiter: String) : Parser {
        init {
            if (delimiter == "") {
                throw IllegalStateException()
            }
        }

        override fun parse(raw: String): List<Any> {

            val parsedList = emptyList<Any>().toMutableList()

            raw.split(delimiter).forEach { item ->
                if (item.isNotEmpty()) {
                    parsedList.add(
                        if (isBoolean(item)) {
                            item.toBoolean()
                        } else if (isNumber(item)) {
                            presentNumber(item)
                        } else if (isChar(item)) {
                            item[0]
                        } else {
                            item
                        }
                    )
                }
            }

            return parsedList.toList()
        }

        private fun isBoolean(item: String): Boolean {
            if (item == "true" || item == "false") {
                return true
            }
            return false
        }

        private fun isChar(item: String): Boolean {
            if (item.length == 1 || item[0].isDigit()) {
                return true
            }
            return false
        }

        private fun isNumber(item: String): Boolean {

            val cleanedItem = item.replace("f", "").replace("-", "").replace(".", "")

            cleanedItem.forEach { c ->
                if (!c.isDigit()) {
                    return false
                }
            }
            return true
        }

        private fun presentNumber(item: String): Number {
            val intPart = mutableListOf<Int>()
            val floatPart = mutableListOf<Int>()
            var isNegative = false

            var pointer = intPart

            item.forEach { c ->
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

            if (floatPart.isNotEmpty() || intPart.size >= 19) {
                var result = BigDecimal.ZERO

                intPart.reversed().forEachIndexed { index, digit ->
                    result = result.plus(BigDecimal(digit).multiply(BigDecimal(10).pow(index)))
                }

                var decimalIndex = 1
                floatPart.forEach { digit ->
                    result = result.plus(BigDecimal(digit).divide(BigDecimal(10).pow(decimalIndex)))
                    decimalIndex++
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
            } else {
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

//        private fun presentNumber(item: String): Number {
//
//            if (hasNonZeroDecimalPart(item) || item.replace("-", "").length >= 19) {
//                val result = item.toDouble()
//
//                if ((Float.MAX_VALUE * -1) <= result && result <= Float.MAX_VALUE) {
//                    return result.toFloat()
//                }
//                return result
//            } else {
//                val result = item.toLong()
//
//                if (Byte.MIN_VALUE <= result && result <= Byte.MAX_VALUE) {
//                    return result.toByte()
//                }
//
//                if (Short.MIN_VALUE <= result && result <= Short.MAX_VALUE) {
//                    return result.toShort()
//                }
//
//                if (Int.MIN_VALUE <= result && result <= Int.MAX_VALUE) {
//                    return result.toInt()
//                }
//
//                return result
//            }
//
//        }

//        private fun hasNonZeroDecimalPart(value: String): Boolean =
//            value.contains('.') && value.split('.')[1].any { it != '0' }
//        }
    }
}