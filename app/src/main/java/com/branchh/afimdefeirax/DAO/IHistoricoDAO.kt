package com.branchh.afimdefeirax.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.branchh.afimdefeirax.Model.HistoricoModel

@Dao
interface IHistoricoDAO {

        @Insert
        fun save(historico: HistoricoModel):Long

        @Update
        fun update(imagem: List<HistoricoModel>):Int

        @Query(value="Select * from Historico where imagem =:imagem")
        fun get(imagem:Int): HistoricoModel

        @Query(value="Select * from Historico")
        fun getAll(): List<HistoricoModel>

}