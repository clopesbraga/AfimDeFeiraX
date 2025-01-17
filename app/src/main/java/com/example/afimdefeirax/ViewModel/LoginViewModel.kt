package com.example.afimdefeirax.ViewModel

import androidx.lifecycle.ViewModel
import com.example.afimdefeirax.Model.LoginModel
import com.example.afimdefeirax.SharedPreferences.ILoginShared
import com.example.afimdefeirax.Utils.FirebaseAuth.FirebaseAuthServiceImpl
import com.example.afimdefeirax.Utils.Monitoring
import com.example.afimdefeirax.ViewModel.State.LoginUiState
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.koin.java.KoinJavaComponent.inject

class LoginViewModel(
    private val loginShared: ILoginShared,
    private val authservice: FirebaseAuthServiceImpl,
    private var skipInit: Boolean = false
) : ViewModel() {


    private val firebase: FirebaseAnalytics = Firebase.analytics
    private val _state: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state


    private val _loginResult = MutableSharedFlow<Boolean>()

    init {

        if (!skipInit) {

            _state.update { currrentState ->

                currrentState.copy(
                    username = authservice.getCurrentUserEmail() ?: ""
                )

            }


        }
    }

    fun onUsernameChange(newUsername: String) {
        _state.update { currentState ->
            currentState.copy(username = newUsername)
        }
    }

    fun onPasswordChange(newPassword: String) {
        _state.update { currentState ->
            currentState.copy(password = newPassword)
        }
    }

    suspend fun login(): Boolean {

        if (_state.value.username.isEmpty()) {
            _state.update { it.copy(isLoading = false, error = "Invalid credentials") }
            return false
        }

        _state.update { it.copy(isLoading = true, error = null) }

        return try {
            val userSaved = loginShared.getString("usuario")
            val success = if (_state.value.username == userSaved) {
                authservice.signInWithEmailAndPassword(
                    _state.value.username,
                    _state.value.password
                )
                true
            } else {
                authservice.createUserWithEmailAndPassword(
                    _state.value.username,
                    _state.value.password
                )
                localSave()
                true
            }

            _state.update { it.copy(isLoading = false, isSuccess = true) }
            firebase.logEvent(Monitoring.Login.LOGIN_SUCCESS, null)
            _loginResult.emit(success)
            true
        } catch (_: Exception) {
            _state.update { it.copy(isLoading = false, error = "Login failed due to invalid credentials") }
            firebase.logEvent(Monitoring.Login.LOGIN_FAILED, null)
            _loginResult.emit(false)
            false
        }
    }


    private fun localSave(): String {

        val modelousuario = LoginModel().apply {
            this.id = id
            this.usuario = _state.value.username
        }
        loginShared.storeString("id", modelousuario.id.toString())
        loginShared.storeString("usuario", modelousuario.usuario)

        return modelousuario.usuario
    }
}


