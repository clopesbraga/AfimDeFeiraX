package com.example.afimdefeirax.State

data class HistoricUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val showTutorial: Boolean = true,
)


