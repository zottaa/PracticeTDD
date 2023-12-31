package com.github.johnnysc.practicetdd

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class MessagesViewModel(
    private val dispatchersList: DispatchersList,
    private val communication: MessagesCommunication.Mutable,
    private val viewModelChain: ViewModelChain,
    private val count: Count = Count.Base()
) : ViewModel() {

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    fun handleInput(message: String) {
        dispatchersList.launchBackground(viewModelScope) {
            val result = viewModelChain.handle(message)
            dispatchersList.changeToUI {
                communication.map(count.increment(MessageUI.User(message)))
                communication.map(count.increment(result))
            }
        }
    }

}