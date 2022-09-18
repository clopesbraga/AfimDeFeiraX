package com.example.afimdefeirax.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.intellij.lang.annotations.JdkConstants
import java.util.*

@Entity(tableName = "Compras")
class ComprasModel {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id:Int=0

    @ColumnInfo(name="produto")
    var produto: String=""

    @ColumnInfo(name="qtd")
    var qtd: String=""

    @ColumnInfo(name="preco")
    var preco: String=""

    @ColumnInfo(name="imagem")
    var imagem: Int=0

    @ColumnInfo(name="data")
    lateinit var data: Date

}