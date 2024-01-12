package com.github.johnnysc.practicetdd


interface ValidationChain {
    fun validate(email: String, password: String, async: HandleAsync)

    abstract class Abstract(
        private val validationText: ValidationText,
        private val uiUpdate: LoginUpdate,
        private val next: ValidationChain
    ) : ValidationChain {
        override fun validate(email: String, password: String, async: HandleAsync) {
            if (validationText.isValid(
                    getValidationValue(email, password),
                    object : ConsumeErrorMessage {
                        override fun consume(errorMessage: String) {
                            uiUpdate.update(getErrorLoginState(errorMessage))
                        }
                    }
                )
            ) {
                next.validate(email, password, async)
            }
        }

        abstract fun getValidationValue(email: String, password: String): String

        abstract fun getErrorLoginState(errorMessage: String): LoginState
    }

    class Process(
        private val interactor: LoginInteractor,
        private val uiUpdate: LoginUpdate
    ) : ValidationChain {
        override fun validate(email: String, password: String, async: HandleAsync) {
            uiUpdate.update(LoginState.Loading)
            async.handleAsync(
                {
                    interactor.login(email, password)
                },
                {
                    uiUpdate.update(it.loginState())
                }
            )
        }
    }

    class Password(
        validationText: ValidationText,
        uiUpdate: LoginUpdate,
        next: ValidationChain
    ) : Abstract(validationText, uiUpdate, next) {
        override fun getValidationValue(email: String, password: String): String = password

        override fun getErrorLoginState(errorMessage: String): LoginState =
            LoginState.PasswordError(errorMessage)
    }

    class Login(
        validationText: ValidationText,
        uiUpdate: LoginUpdate,
        next: ValidationChain
    ) : Abstract(validationText, uiUpdate, next) {
        override fun getValidationValue(email: String, password: String): String = email

        override fun getErrorLoginState(errorMessage: String): LoginState =
            LoginState.LoginError(errorMessage)
    }
}