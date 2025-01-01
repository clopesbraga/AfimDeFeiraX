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


    @Before
    fun setup() {





    }

    @Test
    @Config(sdk=[Build.VERSION_CODES.LOLLIPOP])
    fun Fluxo_normal_do_login(){

        Dado_verificacao_dos_dados()
        Quando_realizar_gravacao()
        Entao_devo_ter_feito_login_com_sucesso()

    }


    private fun Dado_verificacao_dos_dados() {

    }


    private fun Quando_realizar_gravacao(){


    }


    private fun Entao_devo_ter_feito_login_com_sucesso(){



    }

}