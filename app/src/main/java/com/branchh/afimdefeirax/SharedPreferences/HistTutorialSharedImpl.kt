package com.branchh.afimdefeirax.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

class HistTutorialSharedImpl (context: Context):IHistTutorialShared{
    val sharedTutorial:SharedPreferences=
        context.getSharedPreferences("tutorial_prefs",Context.MODE_PRIVATE)


    companion object {
        const val KEY_HIST_TUTORIAL_COMPLETED = "hist_tutorial_completed"
    }


    override fun hasHistTutorialBeenCompleted(): Boolean {
        return sharedTutorial.getBoolean(KEY_HIST_TUTORIAL_COMPLETED, false)

    }

    override fun setHistTutorialCompleted(completed: Boolean){
        sharedTutorial.edit().putBoolean(KEY_HIST_TUTORIAL_COMPLETED, completed).apply()
    }

}