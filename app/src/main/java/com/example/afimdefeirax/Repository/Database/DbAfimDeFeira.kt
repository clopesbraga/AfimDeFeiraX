package com.example.afimdefeirax.Repository.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.afimdefeirax.DAO.IComprasDAO
import com.example.afimdefeirax.DAO.IListaDAO
import com.example.afimdefeirax.DAO.ILoginDAO
import com.example.afimdefeirax.Model.ComprasModel
import com.example.afimdefeirax.Model.ListaModel
import com.example.afimdefeirax.Model.LoginModel

@Database(entities = arrayOf((LoginModel::class),(ComprasModel::class),(ListaModel::class)), version = 3)

abstract class DbAfimDeFeira : RoomDatabase() {

    abstract fun loginDAO(): ILoginDAO
    abstract fun comprasDAO(): IComprasDAO
    abstract fun ListaDAO(): IListaDAO


    companion object {

        private lateinit var DBINSTANCE: DbAfimDeFeira
        fun getDatabase(context: Context): DbAfimDeFeira {
            if (!Companion::DBINSTANCE.isInitialized) {
                synchronized(DbAfimDeFeira::class) {
                    DBINSTANCE =
                        Room.databaseBuilder(context, DbAfimDeFeira::class.java, "DbAfimDeFeira")
                            .addMigrations(MIGRATION_3_4)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return DBINSTANCE
        }

        private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DELETE FROM Login")
                database.execSQL("DELETE FROM Compras")
                database.execSQL("DELETE FROM Lista")

            }
        }

    }

}
