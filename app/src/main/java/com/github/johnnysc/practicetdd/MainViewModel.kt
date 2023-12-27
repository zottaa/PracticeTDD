package com.github.johnnysc.practicetdd

class MainViewModel(
    private val filters: List<GoodFilter>,
    private val products: List<Good>,
    private val communication: Communication<List<Good>>,
    private val filtersCommunication: Communication<List<GoodFilter>>
) {

    private val currentFilters: MutableList<GoodFilter> = mutableListOf()

    fun change(filter: GoodFilter) {

        if (currentFilters.contains(filter)) {
            currentFilters.remove(filter)
        } else {
            currentFilters.add(filter)
        }


        val unsuitableGoods = mutableListOf<Good>()

        currentFilters.forEach { currentFilter ->
            products.forEach { product ->
                if (!product.map(currentFilter)) {
                    unsuitableGoods.add(product)
                }
            }
        }

        val communicationList = products.toMutableList()
        communicationList.removeAll(unsuitableGoods)

        communication.map(communicationList)
        filtersCommunication.map(currentFilters)
    }
}