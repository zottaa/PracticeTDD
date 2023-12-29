package com.github.johnnysc.practicetdd

data class WeatherUiModel(
    private val description: String,
    private val isError: Boolean = false
) {
    fun map(communication: LoginCommunication) {
        communication.map(
            if (isError) {
                LoginState.Error(this)
            } else {
                LoginState.Success(this)
            }
        )
    }
}
