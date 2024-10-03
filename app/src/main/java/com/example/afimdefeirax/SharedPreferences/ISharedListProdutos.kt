package com.example.afimdefeirax.SharedPreferences

import android.content.Context
import com.example.afimdefeirax.Model.Produtos

interface ISharedListProdutos {


    fun saveItems(context: Context, items: List<Produtos>)

    fun loadItems(context: Context): List<Produtos>
}