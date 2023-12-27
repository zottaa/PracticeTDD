package com.github.johnnysc.practicetdd

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface Communication<TypedList> {

    fun observe(owner: LifecycleOwner, observer: Observer<TypedList>)

    fun map(source: TypedList)

}