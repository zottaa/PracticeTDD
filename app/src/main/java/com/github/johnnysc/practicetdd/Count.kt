package com.github.johnnysc.practicetdd

interface Count<T> {

    fun increment(liveData: MyObservable<T>)

    class Base<T>(
        private val incrementValue: Int,
    ) : Count<T> {

        private var counter = 0

        override fun increment(liveData: MyObservable<T>) {
            counter++
            liveData.update(counter.toString() as T)
        }

    }
}