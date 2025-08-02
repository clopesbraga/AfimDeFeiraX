package com.branchh.afimdefeirax.Repository.FeiraRepository

import com.branchh.afimdefeirax.Model.FeirasModel
import com.google.android.gms.maps.model.LatLng

interface IFeiraRepository {
    fun getFeirasLocations(callback: (List<FeirasModel>) -> Unit)
}