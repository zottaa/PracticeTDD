package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel

class MainViewModel<T>(
    private val liveData: MyObservable<T>,
    private val count: Count<T> = Count.Base(1)
) : UpdateObserver<T>, NotifyChanges, ViewModel() {


    override fun updateObserver(observer: MyObserver<T>) =
        liveData.updateObserver(observer)


    override fun notifyChanges() =
        liveData.notifyChanges()

    fun go() {
        count.increment(liveData)
    }

}