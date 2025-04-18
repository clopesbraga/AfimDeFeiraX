package com.example.afimdefeirax.Repository.FeiraRepository

import android.util.Log
import com.example.afimdefeirax.Model.FeirasModel
import com.example.afimdefeirax.Utils.DeviceCurrentTime
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FeirasRepositoryImpl: IFeiraRepository {

    private val database: DatabaseReference =
                    Firebase.database.reference.child("Pesquisa").child("Feiras")

    private val diasemana=DeviceCurrentTime()
    override fun getFeirasLocations(callback: (List<FeirasModel>) -> Unit) {
        val feirasLocations = mutableListOf<FeirasModel>()

        val referenceListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (feirasnapshot in snapshot.children) {
                    val feiras = feirasnapshot.getValue(FeirasModel::class.java)
                    feiras?.let {
                        if (diasemana.trazSemana() == it.dia.trim()) {
                            feirasLocations.add(
                                FeirasModel(
                                    "FEIRA ${it.Feira}",
                                    it.Latitude.toDouble().toString(),
                                    it.Longitude.toDouble().toString(),
                                    it._id,
                                    it.bairro,
                                    it.cidade,
                                    it.dia,
                                    it.endereco
                                )
                            )
                        }
                    }
                }
                callback.invoke(feirasLocations)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FIREBASE", "Failed to read", error.toException())
                callback.invoke(emptyList())
            }
        }
        database.addListenerForSingleValueEvent(referenceListener)
    }
}