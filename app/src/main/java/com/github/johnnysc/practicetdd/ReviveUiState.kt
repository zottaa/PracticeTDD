package com.github.johnnysc.practicetdd

interface ReviveUiState {
    object Initial : ReviveUiState

    data class Secondary(
        private val text: String
    ) : ReviveUiState
}