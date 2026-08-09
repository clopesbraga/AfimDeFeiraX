package com.branchh.afimdefeirax.SharedPreferences

import android.content.Context
import android.content.SharedPreferences

class MapTutorialSharedImpl (context: Context):IMapTutorialShared{
    val sharedTutorial:SharedPreferences=
        context.getSharedPreferences("tutorial_prefs",Context.MODE_PRIVATE)


    companion object {
        const val KEY_MAP_TUTORIAL_COMPLETED = "map_tutorial_completed"
        const val KEY_APP_USAGE_COUNT = "app_usage_count"
    }


    override fun hasMapTutorialBeenCompleted(): Boolean {
        return sharedTutorial.getBoolean(KEY_MAP_TUTORIAL_COMPLETED, false)

    }

    override fun setMapTutorialCompleted(completed: Boolean){
        sharedTutorial.edit().putBoolean(KEY_MAP_TUTORIAL_COMPLETED, completed).apply()
    }

    override fun getAppUsageCount(): Int {
        return sharedTutorial.getInt(KEY_APP_USAGE_COUNT, 0)
    }

    override fun incrementAppUsageCount() {
        val currentCount = getAppUsageCount()
        sharedTutorial.edit().putInt(KEY_APP_USAGE_COUNT, currentCount + 1).apply()
    }

}