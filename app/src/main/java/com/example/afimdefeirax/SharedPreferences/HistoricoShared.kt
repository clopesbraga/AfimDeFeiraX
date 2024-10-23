package com.example.afimdefeirax.SharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.afimdefeirax.Model.Historico
import com.example.afimdefeirax.Model.Produtos
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HistoricoShared( context: Context): ISharedHistorico {

    private val sharedHistorico: SharedPreferences =
        context.getSharedPreferences("Historico", Context.MODE_PRIVATE)
    override fun saveItems(context: Context, items: List<Historico>) {
        val editor = sharedHistorico.edit()
        val gson = Gson()
        val json = gson.toJson(items)
        editor.putString("Historico", json)
        editor.apply()
    }

    override fun loadItems(context: Context): List<Historico> {
        val json = sharedHistorico.getString("Historico", null)
        val gson = Gson()
        val type = object : TypeToken<List<Produtos>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    override fun updateItem(context: Context, itemToUpdate: Historico) {

        val gson = Gson()
        val historicoJson = gson.toJson(itemToUpdate)
        val editor = sharedHistorico.edit()
        editor.putString("${itemToUpdate.imagem}", historicoJson)
        editor.apply()

    }


}