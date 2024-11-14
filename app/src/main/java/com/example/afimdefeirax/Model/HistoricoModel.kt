package com.example.afimdefeirax.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Historico")
class HistoricoModel {

    @ColumnInfo(name="id")
    var id:Int=0

    @ColumnInfo(name="nome")
    var nome: String=""

    @ColumnInfo(name="preco1")
    var preco1: String="0.00"

    @ColumnInfo(name="preco2")
    var preco2: String?="0.00"

    @ColumnInfo(name="preco3")
    var preco3: String?="0.00"

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="imagem")
    var imagem: Int=0
}

data class Historico(
    val nome: String,
    val preco: String,
    val imagem: Int
)