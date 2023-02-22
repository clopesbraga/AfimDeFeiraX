package com.example.afimdefeirax.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.afimdefeirax.Model.ComprasModel
import com.example.afimdefeirax.Model.ListaModel


@Dao
interface IListaDAO {

    @Insert
    fun save(lista:ListaModel):Long

    @Update
    fun update(id: ListaModel):Int

    @Query(value="Select * from Lista where id =:id")
    fun get(id:Int): ListaModel
}