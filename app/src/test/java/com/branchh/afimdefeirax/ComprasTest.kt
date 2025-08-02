package com.branchh.afimdefeirax

import android.app.Activity
import android.os.Build
import com.branchh.afimdefeirax.Model.ComprasModel
import com.branchh.afimdefeirax.Repository.ComprasRepository
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
class ComprasTest {

    lateinit var mComprasviewmodeltest: ComprasRepository
    lateinit var activity:Activity
    lateinit var activityController:ActivityController<Activity>
    lateinit var modelocompratest:ComprasModel
    lateinit var modelousalvobanco:ComprasModel


    @Before
    fun setup() {

        activityController=Robolectric.buildActivity(Activity::class.java).create()
        activity = activityController.resume().get()

        modelocompratest = ComprasModel().apply {
            this.id = 10
            this.produto="produtoTeste"
            this.qtd = "2"
            this.preco = "2.00"
            this.imagem = 1234
            this.data = "01/01/2023"
        }
        mComprasviewmodeltest = ComprasRepository(activity.applicationContext)
    }

    @Test
    @Config(sdk=[Build.VERSION_CODES.LOLLIPOP])
    fun Fluxo_normal_de_Compra(){

        Dado_acesso_tela_produtos()
        Quando_realizar_compra()
        Entao_a_compra_deve_ser_realizada_com_sucesso()

    }


    private fun Dado_acesso_tela_produtos() {
        assertNotNull(modelocompratest.id)
        assertNotNull(modelocompratest.produto)
        assertNotNull(modelocompratest.qtd)
        assertNotNull(modelocompratest.preco)
        assertNotNull(modelocompratest.imagem)
        assertNotNull(modelocompratest.data)
    }


    private fun Quando_realizar_compra(){

        assertEquals(mComprasviewmodeltest.save(modelocompratest),true)

    }


    private fun Entao_a_compra_deve_ser_realizada_com_sucesso(){

        modelousalvobanco = mComprasviewmodeltest.get(modelocompratest.id)
        assertEquals(modelousalvobanco.id,modelocompratest.id)
        assertEquals(modelousalvobanco.produto,modelocompratest.produto)
        assertEquals(modelousalvobanco.qtd,modelocompratest.qtd)
        assertEquals(modelousalvobanco.preco,modelocompratest.preco)
        assertEquals(modelousalvobanco.imagem,modelocompratest.imagem)
        assertEquals(modelousalvobanco.data,modelocompratest.data)

    }

}