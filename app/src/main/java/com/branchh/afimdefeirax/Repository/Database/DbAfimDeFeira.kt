package com.branchh.afimdefeirax.Repository.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.branchh.afimdefeirax.DAO.IComprasDAO
import com.branchh.afimdefeirax.DAO.IHistoricoDAO
import com.branchh.afimdefeirax.DAO.IListaDAO
import com.branchh.afimdefeirax.DAO.ILoginDAO
import com.branchh.afimdefeirax.Model.ComprasModel
import com.branchh.afimdefeirax.Model.HistoricoModel
import com.branchh.afimdefeirax.Model.ListaModel
import com.branchh.afimdefeirax.Model.LoginModel

@Database(
    entities = arrayOf(
        (LoginModel::class),
        (ComprasModel::class),
        (ListaModel::class),
        (HistoricoModel::class)
    ), version = 8
)

abstract class DbAfimDeFeira : RoomDatabase() {

    abstract fun loginDAO(): ILoginDAO
    abstract fun comprasDAO(): IComprasDAO
    abstract fun ListaDAO(): IListaDAO
    abstract fun historicoDAO(): IHistoricoDAO


    companion object {

        private lateinit var DBINSTANCE: DbAfimDeFeira
        fun getDatabase(context: Context): DbAfimDeFeira {
            if (!Companion::DBINSTANCE.isInitialized) {
                synchronized(DbAfimDeFeira::class) {
                    DBINSTANCE =
                        Room.databaseBuilder(context, DbAfimDeFeira::class.java, "DbAfimDeFeira")
                            .addMigrations(MIGRATION_7_8)
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return DBINSTANCE
        }

        private val MIGRATION_7_8: Migration = object : Migration(7, 8) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("DELETE FROM Login")
                db.execSQL("DELETE FROM Compras")
                db.execSQL("DELETE FROM Lista")
                db.execSQL("DELETE FROM Historico")


            }
        }
    }
}
