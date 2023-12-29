package com.github.johnnysc.practicetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface LoginCommunication {

    fun map(source: LoginState)

    fun observe(owner: LifecycleOwner, observer: Observer<LoginState>)

    class Base(
        private val liveData: MutableLiveData<LoginState> = MutableLiveData()
    ) : LoginCommunication {

        override fun map(source: LoginState) {
            liveData.value = source
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<LoginState>) {
            observe(owner, observer)
        }
    }
}