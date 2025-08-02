package com.branchh.afimdefeirax.State

data class ProdutosUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val showTutorial: Boolean = true,
)


