package com.example.afimdefeirax.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.afimdefeirax.Model.Historico
import com.example.afimdefeirax.Model.HistoricoModel
import com.example.afimdefeirax.Repository.HistoricoRepository
import com.example.afimdefeirax.SharedPreferences.HistoricoShared
import org.koin.java.KoinJavaComponent.inject

class HistoricoViewModel(application: Application) : AndroidViewModel(application) {

    private val msavehistorico:  HistoricoRepository by inject(HistoricoRepository::class.java)
    private val historicoShared: HistoricoShared by inject(HistoricoShared::class.java)


    fun loadHistorico(): List<HistoricoModel> {

//        val item =historicoShared.loadItems(getApplication())
//        if (item.isNotEmpty()) {
//            save(item)
//        }

        return  msavehistorico.getAll()

    }

    fun save(item: List<Historico>) {

        val modelohistorico = HistoricoModel().apply {

            item.forEach { item->

                this.nome = item.nome
                this.imagem = item.imagem
                if (this.preco1.isEmpty().or(this.preco1 < item.preco)) {
                    this.preco1 = item.preco
                }
                if (this.preco2.isEmpty().or(this.preco2 < preco1)) {
                    this.preco2 = preco1
                }
                if (this.preco3.isEmpty().or(this.preco3 < preco2)) {
                    this.preco3 = preco2
                }
            }
        }
        msavehistorico.save(modelohistorico)

    }


}