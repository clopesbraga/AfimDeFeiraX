package com.example.afimdefeirax.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapaFeirasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Mostrará as Feiras das cidades"
    }
    val text: LiveData<String> = _text
}