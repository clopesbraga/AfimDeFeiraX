package com.example.afimdefeirax.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.afimdefeirax.Model.LoginModel
import com.example.afimdefeirax.SharedPreferences.LoginShared
import com.example.afimdefeirax.Utils.Monitoring
import com.example.afimdefeirax.ViewModel.State.LoginUiState
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class LoginViewModel(application: Application) : AndroidViewModel(application) {


    private val auth = Firebase.auth
    private val firebase: FirebaseAnalytics = Firebase.analytics
    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.asStateFlow()

    private val msharedlogin = LoginShared(application.applicationContext)


    init {
        _state.update { currrentState ->

            currrentState.copy(
                username = auth.currentUser?.email ?: "",
                auth = auth
            )

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

    fun login():Boolean {
        var response =false

        viewModelScope.launch {

            if (_state.value.username.isNotEmpty()) {

                _state.update { it.copy(isLoading = true, error = null) }

                try {

                    if (_state.value.username == localSave()) {

                        val login = auth.signInWithEmailAndPassword(
                            _state.value.username,
                            _state.value.password
                        ).await()
                        _state.update { it.copy(isLoading = false, isSuccess = true) }
                        response = true
                        firebase.logEvent(Monitoring.Login.LOGIN_SUCCESS,null)
                    } else {

                        val create = auth.createUserWithEmailAndPassword(
                            _state.value.username,
                            _state.value.password
                        ).await()
                        localSave()
                        response = true
                        firebase.logEvent(Monitoring.Login.LOGIN_SUCCESS,null)
                    }

                } catch (e: Exception) {

                    _state.update { it.copy(isLoading = false, error = e.message) }
                    firebase.logEvent(Monitoring.Login.LOGIN_ERROR,null)
                    Log.e(Monitoring.Login.LOGIN_ERROR+": ", "${e.message}")

                }
            } else {
                firebase.logEvent(Monitoring.Login.LOGIN_FAILED,null)
                Log.d(Monitoring.Login.LOGIN_FAILED, "username is empty")            }
        }

        return response
    }


    private fun localSave(): String {

        val modelousuario = LoginModel().apply {
            this.id = id
            this.usuario = _state.value.username
        }
        msharedlogin.storeString("id", modelousuario.id.toString())
        msharedlogin.storeString("usuario", modelousuario.usuario)

        return modelousuario.usuario
    }


}