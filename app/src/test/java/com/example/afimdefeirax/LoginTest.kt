package com.example.afimdefeirax

import android.app.Activity
import android.os.Build
import com.example.afimdefeirax.Model.LoginModel
import com.example.afimdefeirax.Repository.LoginRepository
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
class LoginTest {

    lateinit var mloginviewmodeltest: LoginRepository
    lateinit var activity:Activity
    lateinit var activityController:ActivityController<Activity>
    lateinit var modelousuariotest:LoginModel
    lateinit var modelousalvobanco:LoginModel



    @Before
    fun setup() {

        activityController=Robolectric.buildActivity(Activity::class.java).create()
        activity = activityController.resume().get()

        modelousuariotest = LoginModel().apply {
            this.id = 1
            this.usuario="teste"
            this.nome = "testnome"
            this.senha = "testsenha"

        }

        mloginviewmodeltest = LoginRepository(activity.applicationContext)

    }

    @Test
    @Config(sdk=[Build.VERSION_CODES.LOLLIPOP])
    fun Dado_verificacao_dos_dados() {
        assertNotNull(modelousuariotest.id)
        assertNotNull(modelousuariotest.usuario)
        assertNotNull(modelousuariotest.nome)
        assertNotNull(modelousuariotest.senha)
    }

    @Test
    fun Quando_realizar_gravacao(){

        assertEquals(mloginviewmodeltest.save(modelousuariotest),true)

    }

    @Test
    fun Entao_devo_ter_feito_login_com_sucesso(){

        modelousalvobanco = mloginviewmodeltest.get(modelousuariotest.id)
        assertEquals(modelousalvobanco.id,modelousuariotest.id)
        assertEquals(modelousalvobanco.nome,modelousuariotest.nome)
        assertEquals(modelousalvobanco.usuario,modelousuariotest.usuario)
        assertEquals(modelousalvobanco.senha,modelousuariotest.senha)

    }

}