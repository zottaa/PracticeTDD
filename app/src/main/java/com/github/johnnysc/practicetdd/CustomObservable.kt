package com.github.johnnysc.practicetdd

interface CustomObservable<T : CustomObject, K : CustomObserver<T>> : Update<T> {

    fun addObserver(observer: K)

    fun removeObserver(observer: K)

    class Base<T : CustomObject, K : CustomObserver<T>> : CustomObservable<T, K> {

        private val observers = mutableListOf<K>()

        override fun addObserver(observer: K) {
            observers.add(observer)
        }

        override fun removeObserver(observer: K) {
            observers.remove(observer)
        }

        override fun update(argument: T) {
            observers.forEach {
                if (it.canHandle(argument)) {
                    it.update(argument)
                }
            }
        }
    }
}