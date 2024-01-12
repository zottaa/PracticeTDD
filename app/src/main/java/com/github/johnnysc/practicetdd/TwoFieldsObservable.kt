package com.github.johnnysc.practicetdd

class TwoFieldsObservable(
    private val observer: TwoFieldsObjectObserver
) {
    private var buffer = TwoFieldsObject(-1, "")
    private var currentObj = TwoFieldsObject(-1, "")

    fun accept(id: Int = -1, name: String = "") {
        if (id != -1)
            currentObj.id = id
        if (name.isNotEmpty())
            currentObj.name = name
        if (currentObj.id != buffer.id && currentObj.name != buffer.name) {
            buffer = currentObj.copy()
            observer.notify(currentObj)
        }
    }
}