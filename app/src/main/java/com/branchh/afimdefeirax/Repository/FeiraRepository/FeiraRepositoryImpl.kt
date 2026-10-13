package com.branchh.afimdefeirax.Repository.FeiraRepository

import android.util.Log
import com.branchh.afimdefeirax.Model.FeirasModel
import com.branchh.afimdefeirax.Model.TipoFeiraModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FeirasRepositoryImpl: IFeiraRepository {

    override fun getFeiras(tipo: TipoFeiraModel, callback: (List<FeirasModel>) -> Unit) {
        val dbReference = when (tipo) {
            TipoFeiraModel.NORMAL -> Firebase.database.reference.child("Pesquisa").child("Feiras")
            TipoFeiraModel.ORGANICA -> Firebase.database.reference.child("Pesquisa").child("Organicas")
        }
        val feirasLocations = mutableListOf<FeirasModel>()

            dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (feirasnapshot in snapshot.children) {
                        feirasnapshot.getValue(FeirasModel::class.java)?.let { locFeiras ->
                            feirasLocations.add(
                                FeirasModel(
                                    "Feira: ${locFeiras.Feira}",
                                    locFeiras.Latitude.toDouble().toString(),
                                    locFeiras.Longitude.toDouble().toString(),
                                    locFeiras._id,
                                    locFeiras.bairro,
                                    locFeiras.cidade,
                                    locFeiras.dia,
                                    locFeiras.endereco
                                )
                            )
                        }
                    }
                    callback.invoke(feirasLocations)
                }
            override fun onCancelled(error: DatabaseError) {
                Log.d("FIREBASE", "Failed to read", error.toException())
                callback.invoke(emptyList())
            }
        })
    }
}