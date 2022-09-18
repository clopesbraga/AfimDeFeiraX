package com.example.afimdefeirax.Repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.afimdefeirax.DAO.ILoginDAO
import com.example.afimdefeirax.Model.LoginModel

@Database(entities = arrayOf((LoginModel::class)), version = 1)
abstract class DbAfimDeFeira: RoomDatabase() {

     abstract fun loginDAO(): ILoginDAO

    companion object{

    private lateinit var DBINSTANCE: DbAfimDeFeira
    fun getDatabase(context: Context):DbAfimDeFeira{
        if(!::DBINSTANCE.isInitialized){
            synchronized(DbAfimDeFeira::class){
            DBINSTANCE= Room.databaseBuilder(context,DbAfimDeFeira::class.java,"DbAfimDeFeira")
                .allowMainThreadQueries()
                .build()
            }
        }
        return DBINSTANCE
    }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Login")
            }
        }

}

}
