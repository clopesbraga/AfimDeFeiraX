package com.branchh.afimdefeirax.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Lista")
class ListaModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id:Int=0

    @ColumnInfo(name="nome")
    var nome: String=""

    @ColumnInfo(name="qtd")
    var qtd: String=""

    @ColumnInfo(name="unidade")
    var unidade: String=""

    @ColumnInfo(name="data")
    var data: String=""

    @ColumnInfo(name="imagem")
    var imagem: Int=0

    @ColumnInfo(name="categoria")
    var categoria: String=""

}