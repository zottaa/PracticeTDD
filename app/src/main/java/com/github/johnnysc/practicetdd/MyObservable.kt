package com.github.johnnysc.practicetdd


interface MyObservable<T> : Update<T>, UpdateObserver<T>, NotifyChanges {

    abstract class Abstract<T> : MyObservable<T> {

        protected var data: T? = null

        protected var observer: MyObserver<T> = MyObserver.Empty()
        override fun update(value: T) {
            data = value
            observer.update(value)
        }

        override fun updateObserver(observer: MyObserver<T>) {
            this.observer = observer
        }

        override fun notifyChanges() {
            if (data != null)
                observer.update(data!!)
        }
    }

    class Base<T> : Abstract<T>()

    class SingleLiveEvent<T> : Abstract<T>() {

        private var isLiveDataAndObserverValuesEqual = false
        override fun update(value: T) {
            super.update(value)
            isLiveDataAndObserverValuesEqual = true
        }

        override fun updateObserver(observer: MyObserver<T>) {
            super.updateObserver(observer)
            isLiveDataAndObserverValuesEqual = false
        }

        override fun notifyChanges() {
            if (isLiveDataAndObserverValuesEqual.not() && data != null) {
                observer.update(data!!)
                isLiveDataAndObserverValuesEqual = true
            }
        }
    }
}

interface Update<T> {
    fun update(value: T)
}

interface UpdateObserver<T> {
    fun updateObserver(observer: MyObserver<T>)
}

interface NotifyChanges {
    fun notifyChanges()
}