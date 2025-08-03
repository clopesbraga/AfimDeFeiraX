package com.branchh.afimdefeirax.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Login")
class LoginModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id:Int=0

    @ColumnInfo(name="usuario")
    var usuario: String=""

}