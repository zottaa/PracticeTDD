package com.github.johnnysc.practicetdd

interface NewInt {

    fun isValid(number: Int): Boolean

        class Positive(private val extraValidation: NewInt = Empty()) : NewInt {

        override fun isValid(number: Int): Boolean = extraValidation.isValid(number) && number > 0

    }

    private class Empty : NewInt {

        override fun isValid(number: Int): Boolean = true

    }

    class Negative(private val extraValidation: NewInt = Empty()) : NewInt {

        override fun isValid(number: Int): Boolean = extraValidation.isValid(number) && number < 0

    }

    class Odd(private val extraValidation: NewInt = Empty()) : NewInt {

        override fun isValid(number: Int): Boolean = extraValidation.isValid(number) && number % 2 != 0

    }

    class Less(
        private val limit: Int,
        private val extraValidation: NewInt = Empty()
    ) : NewInt {

        override fun isValid(number: Int): Boolean {
            return extraValidation.isValid(number) && number < limit
        }

    }
}