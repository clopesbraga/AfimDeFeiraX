package com.example.afimdefeirax

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.afimdefeirax.Model.LoginModel
import com.example.afimdefeirax.Repository.DbAfimDeFeira
import com.example.afimdefeirax.Repository.LoginRepository
import com.example.afimdefeirax.ViewModel.LoginViewModel
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginTest {

    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun useAppContext() {
        // Context of the app under test.
        assertEquals("com.example.afimdefeirax", appContext.packageName)
    }

    @Test
    fun conexaoBancoTest() {

        assertNotNull(DbAfimDeFeira.getDatabase(appContext).loginDAO())

    }

    @Test
    fun gravarLoginTest(){



    }



}
