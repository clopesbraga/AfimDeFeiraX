package com.branchh.afimdefeirax.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.branchh.afimdefeirax.Model.ComprasModel


@Dao
interface IComprasDAO {

    @Insert
    fun save(compras:ComprasModel):Long

    @Update
    fun update(id: ComprasModel):Int

    @Query(value="Select * from Compras where id =:id")
    fun get(id:Int): ComprasModel
}