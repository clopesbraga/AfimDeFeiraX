package com.example.afimdefeirax.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.afimdefeirax.Model.LoginModel

@Dao
interface ILoginDAO{

    @Insert
    fun save(usuario:LoginModel):Long

    @Update
    fun update(id:LoginModel):Int

    @Query(value="Select * from Login where id =:id")
    fun get(id:Int):LoginModel

}