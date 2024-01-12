package com.github.johnnysc.practicetdd

interface ValidatePassword {
    fun validate(input: String)
}

data class PasswordInvalidException(override val message: String) : Exception()