package com.github.johnnysc.practicetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface MessagesCommunication {

    interface Observe : com.github.johnnysc.practicetdd.Observe<MessageUI>, MessagesCommunication

    interface Map : com.github.johnnysc.practicetdd.Map<MessageUI>, MessagesCommunication

    interface Mutable : Observe, Map

}

interface Observe<T : Any> {
    fun observe(owner: LifecycleOwner, observer: Observer<List<T>>)
}

interface Map<T : Any> {
    fun map(data: T)
}