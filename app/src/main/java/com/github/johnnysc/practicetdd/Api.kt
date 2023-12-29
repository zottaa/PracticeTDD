package com.github.johnnysc.practicetdd

interface Api {

    fun fetch(body: RequestBody, callback: Callback)

    interface Callback {
        fun provideError(result: Result.Error)

        fun provideSuccess(result: Result.Success)
    }

    interface RequestBody {
        fun isEmpty(): Boolean

        class Base(private val body: String) : RequestBody {
            override fun isEmpty(): Boolean =
                body.isEmpty()
        }
    }

    interface Result {

        fun map(callback: Repository.DataCallback)

        class Error(private val e: IllegalStateException) : Result {
            override fun map(callback: Repository.DataCallback) {
                callback.provideError(e.message.toString())
            }

        }

        class Success(private val data: String) : Result {
            override fun map(callback: Repository.DataCallback) {
                callback.provideSuccess(data)
            }

        }
    }

}