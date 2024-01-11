package com.github.johnnysc.practicetdd

class ReviveViewModel(
    private val communication: ReviveCommunication
) {
    fun restore(bundle: SaveAndRestore) {
        if (!bundle.isEmpty())
            communication.map(bundle.restore())
        else
            communication.map(ReviveUiState.Initial)
    }

    fun show(text: String) {
        communication.map(ReviveUiState.Secondary(text))
    }

    fun save(bundle: SaveAndRestore) {
        communication.save(bundle)
    }
}