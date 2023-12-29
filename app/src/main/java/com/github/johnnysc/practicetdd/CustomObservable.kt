package com.github.johnnysc.practicetdd

interface CustomObservable<ObjectType : Any, ObserverType : CustomObserver<ObjectType>> :
    Update<ObjectType> {

    fun addObserver(observer: ObserverType)

    fun removeObserver(observer: ObserverType)

    class Base<ObjectType : Any, ObserverType : CustomObserver<ObjectType>> :
        CustomObservable<ObjectType, ObserverType> {

        private val observersList = mutableListOf<ObserverType>()

        override fun addObserver(observer: ObserverType) {
            observersList.add(observer)
        }

        override fun removeObserver(observer: ObserverType) {
            observersList.remove(observer)
        }

        override fun update(argument: ObjectType) {
            observersList.forEach {
                it.update(argument)
            }
        }
    }
}