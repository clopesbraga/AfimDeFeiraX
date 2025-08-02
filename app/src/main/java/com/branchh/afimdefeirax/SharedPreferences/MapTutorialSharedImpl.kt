package com.branchh.afimdefeirax.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

class MapTutorialSharedImpl (context: Context):IMapTutorialShared{
    val sharedTutorial:SharedPreferences=
        context.getSharedPreferences("tutorial_prefs",Context.MODE_PRIVATE)


    companion object {
        const val KEY_MAP_TUTORIAL_COMPLETED = "map_tutorial_completed"
    }


    override fun hasMapTutorialBeenCompleted(): Boolean {
        return sharedTutorial.getBoolean(KEY_MAP_TUTORIAL_COMPLETED, false)

    }

    override fun setMapTutorialCompleted(completed: Boolean){
        sharedTutorial.edit().putBoolean(KEY_MAP_TUTORIAL_COMPLETED, completed).apply()
    }

}