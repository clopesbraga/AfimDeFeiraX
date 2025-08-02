package com.branchh.afimdefeirax.State

data class LoginUiState(
    val username: String="",
    val password: String="",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
)

