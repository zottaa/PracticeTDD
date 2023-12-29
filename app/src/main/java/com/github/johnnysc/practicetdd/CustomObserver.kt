package com.github.johnnysc.practicetdd

interface CustomObserver<T: CustomObject> : Update<T> {

    fun canHandle(obj: T) : Boolean

    abstract class Premium<T : CustomObject> : CustomObserver<T> {
        override fun canHandle(obj: T): Boolean = true
    }

    abstract class Usual<T : CustomObject> : CustomObserver<T> {
        override fun canHandle(obj: T): Boolean {
            return obj is CustomObject.Usual
        }
    }
}