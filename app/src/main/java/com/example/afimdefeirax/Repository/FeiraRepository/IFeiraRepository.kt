package com.example.afimdefeirax.Repository.FeiraRepository

import com.example.afimdefeirax.Model.FeirasModel
import com.google.android.gms.maps.model.LatLng

interface IFeiraRepository {
    fun getFeirasLocations(callback: (List<FeirasModel>) -> Unit)
}