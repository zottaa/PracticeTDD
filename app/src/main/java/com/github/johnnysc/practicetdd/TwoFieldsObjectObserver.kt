package com.github.johnnysc.practicetdd

interface TwoFieldsObjectObserver {
    fun notify(obj: TwoFieldsObject)
}

data class TwoFieldsObject(
    var id: Int,
    var name: String
    )