package com.branchh.afimdefeirax.SharedPreferences

import android.content.Context
import androidx.core.content.edit
import com.branchh.afimdefeirax.Model.Produtos

interface ISharedListProdutos {


    fun saveItems(context: Context, items: List<Produtos>)

    fun loadItems(context: Context): List<Produtos>

    fun removeItem(context: Context, itemToRemove: Produtos)

    fun updateItem(context: Context, updatedItem: Produtos)

    fun saveTotalSum(total: Int)

    fun loadTotalConfirmed():Int

    fun resetTotalSum()

}