package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel

class PagingViewModel(
    private val repository: PagingRepository,
    private val communication: Communication,
) : ViewModel() {

    fun init(isFirstRun: Boolean) {
        if (isFirstRun)
            communication.map(
                repository.messages(PagingRepository.Strategy.INIT).map { it.toUi() }
            )
    }

    fun loadMore() = communication.map(
        repository.messages(PagingRepository.Strategy.NEXT).map { it.toUi() }
    )


    fun loadPrevious() = communication.map(
        repository.messages(PagingRepository.Strategy.PREVIOUS).map { it.toUi() }
    )
}