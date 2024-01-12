package com.github.johnnysc.practicetdd

interface ValidateLogin {

    fun validate(input: String)
}

data class LoginInvalidException(override val message: String) : Exception()