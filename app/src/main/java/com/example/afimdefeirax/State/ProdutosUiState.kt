package com.example.afimdefeirax.State

import com.example.afimdefeirax.Model.Produtos

data class ProdutosUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
)


