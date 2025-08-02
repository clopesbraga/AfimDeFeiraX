package com.branchh.afimdefeirax.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.branchh.afimdefeirax.Model.LoginModel
import com.branchh.afimdefeirax.SharedPreferences.ILoginShared
import com.branchh.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.branchh.afimdefeirax.Utils.FirebaseAuth.FirebaseAuthServiceImpl
import com.branchh.afimdefeirax.Utils.Monitoring
import com.branchh.afimdefeirax.State.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginShared: ILoginShared,
    private val authservice: FirebaseAuthServiceImpl,
    private val analyticservice: FirebaseAnalyticsImpl,
    skipInit: Boolean = false
) : ViewModel() {

    private val messageUserAlreadyRegistered ="The email address is already in use by another account."
    private val _state: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state

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

    fun login(): Boolean {
        var result = false
        viewModelScope.launch { result = onlogin() }
        return result
    }

    private suspend fun onlogin(): Boolean {
        var result = false

        if (_state.value.username.isEmpty()) {
            _state.update { it.copy(isLoading = false, error = "Invalid credentials") }
            analyticservice.firebaselogEvent(Monitoring.Login.LOGIN_ERROR)
            return false
        }

        _state.update { it.copy(isLoading = true, error = null) }
        analyticservice.firebaselogEvent(Monitoring.Login.LOGIN_PROCESS)

        try {
            val userSaved = loginShared.getString("usuario")

            when (userSaved.isNotEmpty()) {
                true -> {
                    signWithEmail()
                    analyticservice.firebaselogEvent(Monitoring.Login.LOGIN_SUCCESS)
                    result = true
                }
                false -> {
                    createUserWithEmailAndPassword()
                    analyticservice.firebaselogEvent(Monitoring.Login.CREATE_USER_SUCESS)
                    localSave()
                    result = true
                }
            }
            _state.update { it.copy(isLoading = false, isSuccess = true) }

        } catch (error: Exception) {

            if (error.message.equals(messageUserAlreadyRegistered)){
                localSave()
                return onlogin()
            }
                _state.update { it.copy(isLoading = false, error = Monitoring.Login.LOGIN_FAILED) }
                analyticservice.firebaselogEvent(Monitoring.Login.LOGIN_FAILED)
                Log.e(Monitoring.Login.LOGIN_FAILED, error.message.toString())
                result = false
        }
        return result
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

    private suspend fun signWithEmail() {
        authservice.signInWithEmailAndPassword(
            _state.value.username,
            _state.value.password
        )
    }

    private suspend fun createUserWithEmailAndPassword() {
        authservice.createUserWithEmailAndPassword(
            _state.value.username,
            _state.value.password
        )
    }
}


