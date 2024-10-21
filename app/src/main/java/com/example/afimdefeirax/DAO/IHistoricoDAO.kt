package com.example.afimdefeirax.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.afimdefeirax.Model.HistoricoModel

@Dao
interface IHistoricoDAO {

        @Insert
        fun save(historico: HistoricoModel):Long

        @Update
        fun update(id: HistoricoModel):Int

        @Query(value="Select * from Historico where id =:id")
        fun get(id:Int): HistoricoModel

}