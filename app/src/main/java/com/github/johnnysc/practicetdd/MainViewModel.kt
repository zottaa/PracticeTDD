package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel

class MainViewModel(
    private val filters: List<GoodFilter>,
    private val products: List<Good>,
    private val communication: Communication<List<Good>>,
    private val filtersCommunication: Communication<List<GoodFilter>>
) : ViewModel(), Change {

    init {
        communication.map(products)
        filtersCommunication.map(filters)
    }


    override fun change(filter: GoodFilter) {
        filters.find { it == filter }?.change(filter)
        filtersCommunication.map(filters)
        products.filter { product ->
            product.map(Good.Base(filters.filter { it.isChosen() }))
        }.let {
            communication.map(it)
        }
    }
}

interface Change {
    fun change(filter: GoodFilter)
}