package com.example.afimdefeirax.State
import com.example.afimdefeirax.R.mipmap.ic_bandeira_saopaulo


data class MapFeirasUIState(
    var selectedCity: String ="SAO PAULO",
    var searchQuery: String = "",
    var cityImages: Int = ic_bandeira_saopaulo,
    var showBottomSheet: Boolean = false,
    var neighborhoodsToShow: List<String> = emptyList(),
    val errorMessage: String="",
    val showTutorial: Boolean = true
)