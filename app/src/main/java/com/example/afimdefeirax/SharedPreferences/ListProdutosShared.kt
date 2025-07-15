package com.example.afimdefeirax.SharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.afimdefeirax.Model.Produtos
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ListProdutosShared(context: Context):ISharedListProdutos {


    private val sharedProdutosList: SharedPreferences =
        context.getSharedPreferences("Produtos", Context.MODE_PRIVATE)

    override fun saveItems(context: Context, items: List<Produtos>) {
        val editor = sharedProdutosList.edit()
        val gson = Gson()
        val json = gson.toJson(items)
        editor.putString("Produtos", json)
        editor.apply()
    }

    override fun loadItems(context: Context): List<Produtos> {
        val json = sharedProdutosList.getString("Produtos", null)
        val gson = Gson()
        val type = object : TypeToken<List<Produtos>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    override fun removeItem(context: Context, itemToRemove: Produtos) {
        val items = loadItems(context).toMutableList()
        items.remove(itemToRemove)
        saveItems(context, items)
    }

    override fun updateItem(context: Context, updatedItem: Produtos) {
        val items = loadItems(context).toMutableList()
        items.add(updatedItem)
        saveItems(context, items)
    }

}