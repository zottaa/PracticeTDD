package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val communication: LoginCommunication,
    private val interactor: LoginInteractor,
    private val mapper: WeatherUiMapper<WeatherUiModel>,
    private val validateEmail: UiValidator,
    private val validatePassword: UiValidator,
    private val dispatchers: DispatchersList
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun login(email: String, password: String) {
        if (validateEmail.isValid(email) && validatePassword.isValid(password)) {
            viewModelScope.launch(dispatchers.io()) {
                val result = interactor.login()
                withContext(dispatchers.ui()) {
                    result.map(mapper).map(communication)
                }
            }
        } else if (validateEmail.isValid(email)) {
            communication.map(LoginState.PasswordError(validatePassword.errorMessage()))
        } else if (validatePassword.isValid(password)) {
            communication.map(LoginState.EmailError(validateEmail.errorMessage()))
        } else {
            communication.map(
                LoginState.TwoErrors(
                    validateEmail.errorMessage(),
                    validatePassword.errorMessage()
                )
            )
        }
    }

}