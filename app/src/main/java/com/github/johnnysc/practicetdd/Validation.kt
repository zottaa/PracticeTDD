package com.github.johnnysc.practicetdd


interface Validation {

    fun isValid(text: String): Validation.Result

    class Password(
        private val minLength: Int = 1,
        private val upperCaseLettersCount: Int = 0,
        private val lowerCaseLettersCount: Int = 0,
        private val numbersCount: Int = 0,
        private val specialSignsCount: Int = 0
    ) : Validation {

        init {
            if (minLength < 1) {
                throw IllegalStateException()
            }
            if (upperCaseLettersCount < 0) {
                throw IllegalStateException()
            }
            if (lowerCaseLettersCount < 0) {
                throw IllegalStateException()
            }
            if (numbersCount < 0) {
                throw IllegalStateException()
            }
            if (specialSignsCount < 0) {
                throw IllegalStateException()
            }
        }

        override fun isValid(text: String): Result {

            if (text.length < minLength) {
                return Validation.Result.MinLengthInsufficient(minLength)
            }

            if (upperCaseRegexPattern.toRegex().findAll(text).count() < upperCaseLettersCount) {
                return Validation.Result.UpperCaseLettersCountInsufficient(upperCaseLettersCount)
            }

            if (lowerCaseRegexPattern.toRegex().findAll(text).count() < lowerCaseLettersCount) {
                return Validation.Result.LowerCaseLettersCountInsufficient(lowerCaseLettersCount)
            }

            if (numbersRegexPattern.toRegex().findAll(text).count() < numbersCount) {
                return Validation.Result.NumbersCountInsufficient(numbersCount)
            }

            if (specialSignsRegexPattern.toRegex().findAll(text).count() < specialSignsCount) {
                return Validation.Result.SpecialSignsInsufficient(specialSignsCount)
            }

            return Validation.Result.Valid
        }

        companion object {
            private const val upperCaseRegexPattern  = "[A-Z]"
            private const val lowerCaseRegexPattern = "[a-z]"
            private const val numbersRegexPattern = "[0-9]"
            private const val specialSignsRegexPattern = "[!@$#*%? ]"
        }
    }

    interface Result {
        object Valid : Result

        data class MinLengthInsufficient(private val minLength: Int) : Result

        data class UpperCaseLettersCountInsufficient(private val upperCaseLettersCount: Int) :
            Result

        data class LowerCaseLettersCountInsufficient(private val lowerCaseLettersCount: Int) :
            Result

        data class NumbersCountInsufficient(private val numbersCount: Int) : Result

        data class SpecialSignsInsufficient(private val specialSignsCount: Int) : Result
    }
}