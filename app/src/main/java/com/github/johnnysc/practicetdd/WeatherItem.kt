package com.github.johnnysc.practicetdd

interface WeatherItem {

    fun <T> map(mapper: WeatherUiMapper<T>) : T

    data class Basic(
        private val description: String,
        private val temp: Int,
        private val feelsLike: Int
    ) : WeatherItem {
        override fun <T> map(mapper: WeatherUiMapper<T>) : T  {
            return mapper.map(feelsLike, description, temp)
        }
    }

    data class Error(
        private val exceptionType: ExceptionType
    ) : WeatherItem {
        override fun <T> map(mapper: WeatherUiMapper<T>) : T {
            return mapper.map(exceptionType)
        }
    }

}