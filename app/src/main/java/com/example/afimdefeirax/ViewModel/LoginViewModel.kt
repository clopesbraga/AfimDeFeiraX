package com.example.afimdefeirax.ViewModel

import androidx.lifecycle.ViewModel
import com.example.afimdefeirax.Model.LoginModel
import com.example.afimdefeirax.SharedPreferences.ILoginShared
import com.example.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.example.afimdefeirax.Utils.FirebaseAuth.FirebaseAuthServiceImpl
import com.example.afimdefeirax.Utils.Monitoring
import com.example.afimdefeirax.State.LoginUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    private val loginShared: ILoginShared,
    private val authservice: FirebaseAuthServiceImpl,
    private val analyticservice: FirebaseAnalyticsImpl,
    private var skipInit: Boolean = false
) : ViewModel() {


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
            analyticservice.firebaselogEvent(Monitoring.Login.LOGIN_PROCESS)
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
            analyticservice.firebaselogEvent(Monitoring.Login.LOGIN_SUCCESS)
            _loginResult.emit(success)
            true
        } catch (_: Exception) {
            _state.update { it.copy(isLoading = false, error = Monitoring.Login.LOGIN_FAILED) }
            analyticservice.firebaselogEvent(Monitoring.Login.LOGIN_FAILED)
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


