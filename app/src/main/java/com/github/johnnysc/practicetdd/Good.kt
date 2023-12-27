package com.github.johnnysc.practicetdd

interface Good {

    fun <Type> map(mapper: Good.Mapper<Type>) : Type

    interface Mapper<Type> {
        fun map(
            ram: Int,
            os: OS,
            displaySize: Double,
            processor: ProcessorType,
            price: Double
        ) : Type
    }

    class Base(private val filters: List<GoodFilter>) : Mapper<Boolean> {
        override fun map(
            ram: Int,
            os: OS,
            displaySize: Double,
            processor: ProcessorType,
            price: Double
        ): Boolean =
            filters.all { it.map(ram, os, displaySize, processor, price) }

    }
}