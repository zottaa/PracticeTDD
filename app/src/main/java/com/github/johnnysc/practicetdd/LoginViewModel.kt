package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class LoginViewModel(
    private val validationChain: ValidationChain,
    private val runAsync: RunAsync
) : HandleAsync, ViewModel() {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun login(email: String, password: String) {
        validationChain.validate(email, password, this)
    }

    override fun <T : Any> handleAsync(backgroundBlock: suspend () -> T, uiBlock: (T) -> Unit) {
        runAsync.handleAsync(viewModelScope, backgroundBlock, uiBlock)
    }
}

