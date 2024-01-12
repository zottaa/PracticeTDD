package com.github.johnnysc.practicetdd

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class LoginViewModel(
    private val uiUpdate: LoginUpdate,
    private val validateLogin: ValidateLogin,
    private val validatePassword: ValidatePassword,
    private val interactor: LoginInteractor,
    private val runAsync: RunAsync
) {

    fun login(login: String, password: String) {
        try {
            validateLogin.validate(login)
        } catch (e: LoginInvalidException) {
            return uiUpdate.update(LoginState.LoginError(e.message))
        }

        try {
            validatePassword.validate(password)
        } catch (e: PasswordInvalidException) {
            return uiUpdate.update(LoginState.PasswordError(e.message))
        }

        uiUpdate.update(LoginState.Loading)

        runAsync.handleAsync(
            CoroutineScope(SupervisorJob() + Dispatchers.IO),
            {
                interactor.login(login, password)
            },
            {
                uiUpdate.update(it.loginState())
            }
        )
    }
}