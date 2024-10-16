package com.example.afimdefeirax.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Historico")
class HistoricoModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id:Int=0

    @ColumnInfo(name="preco1")
    var preco1: String=""

    @ColumnInfo(name="preco2")
    var preco2: String=""

    @ColumnInfo(name="preco3")
    var preco3: String=""

    @ColumnInfo(name="imagem")
    var imagem: Int=0
}