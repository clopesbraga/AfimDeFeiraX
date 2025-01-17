package com.example.afimdefeirax.ViewModel.State

import com.google.firebase.auth.FirebaseAuth


data class LoginUiState(
    val username: String="",
    val password: String="",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
)

