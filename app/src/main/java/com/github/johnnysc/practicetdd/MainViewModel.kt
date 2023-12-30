package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel

class MainViewModel(
    private val repository: Repository,
    private val communication: Communication,
    private val schedulersList: SchedulersList
) : ViewModel() {

    fun fetch() {
        schedulersList.io().scheduleDirect {
            val result = repository.fetch().onErrorReturn {
                it.message
            }
            schedulersList.ui().scheduleDirect {
                communication.map(result.blockingGet())
            }
        }
    }
}