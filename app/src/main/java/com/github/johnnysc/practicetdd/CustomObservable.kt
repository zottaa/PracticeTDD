package com.github.johnnysc.practicetdd

interface CustomObservable<T : Any, K : CustomObserver<T>> : Update<T> {

    fun addObserver(observer: K)

    fun removeObserver(observer: K)

    class Base<T : Any, K : CustomObserver<T>>(
        private val maxCount: Int
    ) : CustomObservable<T, K> {

        private val observers: MutableList<K> = mutableListOf<K>()

        override fun addObserver(observer: K) {
            observers.add(observer)
        }

        override fun removeObserver(observer: K) {
            observers.remove(observer)
        }

        override fun update(argument: T) {
            var start = 0
            if (observers.size > maxCount) {
                start = maxCount
            }
            for (i in start until observers.size) {
                observers[i].update(argument)
            }
        }
    }
}