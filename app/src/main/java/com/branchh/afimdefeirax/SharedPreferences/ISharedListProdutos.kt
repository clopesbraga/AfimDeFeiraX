package com.branchh.afimdefeirax.SharedPreferences

import android.content.Context
import com.branchh.afimdefeirax.Model.Produtos

interface ISharedListProdutos {


    fun saveItems(context: Context, items: List<Produtos>)

    fun loadItems(context: Context): List<Produtos>

    fun removeItem(context: Context, itemToRemove: Produtos)

    fun updateItem(context: Context, updatedItem: Produtos)
}