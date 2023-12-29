package com.github.johnnysc.practicetdd

interface Repository {

    fun fetch(body: String, callback: DataCallback)

    interface DataCallback {
        fun provideSuccess(data: String)

        fun provideError(message: String)
    }

    class Base(private val api: Api) : Repository {
        override fun fetch(body: String, callback: DataCallback) {
            api.fetch(
                Api.RequestBody.Base(body),
                object : Api.Callback {
                    override fun provideError(result: Api.Result.Error) {
                        result.map(callback::provideError)
                    }

                    override fun provideSuccess(result: Api.Result.Success) {
                        result.map(callback::provideSuccess)
                    }
                })
        }
    }
}