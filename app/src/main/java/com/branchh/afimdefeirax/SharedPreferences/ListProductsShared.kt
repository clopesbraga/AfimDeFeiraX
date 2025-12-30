package com.branchh.afimdefeirax.SharedPreferences

import android.content.Context
import android.content.SharedPreferences
import com.branchh.afimdefeirax.Model.Produtos
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.core.content.edit


class ListProductsShared(context: Context):ISharedListProdutos {


    private val sharedProdutosList: SharedPreferences =
        context.getSharedPreferences("Produtos", Context.MODE_PRIVATE)

    override fun saveItems(context: Context, items: List<Produtos>) {
        sharedProdutosList.edit() {
            val gson = Gson()
            val json = gson.toJson(items)
            putString("Produtos", json)
        }
    }

    override fun loadItems(listprodutoscontext: Context): List<Produtos> {
        val json = sharedProdutosList.getString("Produtos", null)
        val gson = Gson()
        val type = TypeToken.getParameterized(List::class.java, Produtos::class.java).type
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

    override fun saveTotalSum(total: Int) {
        sharedProdutosList.edit {
            putInt("TOTAL_SUM_KEY", total)
        }
    }

    override fun loadTotalConfirmed(): Int {
        return sharedProdutosList.getInt("TOTAL_SUM_KEY", 0)
    }

    override fun resetTotalSum() {
        sharedProdutosList.edit {
            putInt("TOTAL_SUM_KEY", 0)
        }
    }

}