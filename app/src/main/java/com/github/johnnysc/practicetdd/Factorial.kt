package com.github.johnnysc.practicetdd

import java.math.BigInteger

interface Factorial<T> {

    fun value(number: T): T

    class Int : Factorial<kotlin.Int> {
        override fun value(number: kotlin.Int) : kotlin.Int {
            var result = 1
            for (i in 1..number) {
                result *= i
            }
            return result
        }
    }

    class Double : Factorial<kotlin.Double> {
        override fun value(number: kotlin.Double) : kotlin.Double {
            var result = 1.0
            for (i in 1..number.toInt()) {
                result *= i
            }
            return result
        }

    }

    class BigInteger : Factorial<java.math.BigInteger> {
        override fun value(number: java.math.BigInteger) : java.math.BigInteger {
            var result = java.math.BigInteger.ONE
            for (i in 1..number.toInt()) {
                result = result.times(BigInteger(i.toString()))
            }
            return result
        }
    }

    class Factory(
        private val int: Factorial<kotlin.Int>,
        private val double: Factorial<kotlin.Double>,
        private val bigInteger: Factorial<java.math.BigInteger>
    ) : Factorial<Number> {

        override fun value(number: Number): Number {
            if (number.toInt() < 0) {
                throw IllegalArgumentException()
            }
            if (number in 0..12) {
                return int.value(number.toInt())
            }
            if (number in 13..170) {
                return double.value(number.toDouble())
            }
            if (number in 170..11000) {
                return bigInteger.value(BigInteger(number.toString()))
            }
            throw IllegalStateException()
        }
    }

}