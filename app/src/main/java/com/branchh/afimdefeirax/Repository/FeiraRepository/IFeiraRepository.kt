package com.branchh.afimdefeirax.Repository.FeiraRepository

import com.branchh.afimdefeirax.Model.FeirasModel
import com.branchh.afimdefeirax.Model.TipoFeiraModel
import com.google.android.gms.maps.model.LatLng

interface IFeiraRepository {
    fun getFeiras(tipo:TipoFeiraModel, callback: (List<FeirasModel>) -> Unit)
}