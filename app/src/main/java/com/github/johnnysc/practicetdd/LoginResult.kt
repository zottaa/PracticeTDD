package com.github.johnnysc.practicetdd

interface LoginResult {

    fun loginState() : LoginState
    object Success : LoginResult {
        override fun loginState(): LoginState =
            LoginState.Success

    }

    data class Failed(private val message: String) : LoginResult {
        override fun loginState(): LoginState =
            LoginState.Failed(message)
    }
}