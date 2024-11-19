package com.example.afimdefeirax.SharedPreferences

import android.content.Context
import com.example.afimdefeirax.Model.Historico

interface ISharedHistorico {

    fun saveItems(context: Context, items: List<Historico>)

    fun loadItems(context: Context): List<Historico>

    fun updateItem(context: Context, itemToUpdate: Historico)

    fun clearItems(context: Context)

}