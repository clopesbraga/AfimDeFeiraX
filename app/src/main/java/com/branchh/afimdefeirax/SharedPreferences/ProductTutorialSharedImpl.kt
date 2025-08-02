package com.branchh.afimdefeirax.SharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class ProductTutorialSharedImpl (context: Context):IProductTutorialShared{
    val sharedTutorial:SharedPreferences=
        context.getSharedPreferences("tutorial_prefs",Context.MODE_PRIVATE)

    companion object {
        const val KEY_PRODUCT_TUTORIAL_COMPLETED = "product_tutorial_completed"
    }

    override fun hasProducTutorialBeenCompleted(): Boolean {
        return sharedTutorial.getBoolean(KEY_PRODUCT_TUTORIAL_COMPLETED, false)    }

    override fun setProductTutorialCompleted(completed: Boolean) {
        sharedTutorial.edit() { putBoolean(KEY_PRODUCT_TUTORIAL_COMPLETED, completed) }

    }

}