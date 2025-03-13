package com.example.afimdefeirax

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.afimdefeirax.Model.Produtos
import com.example.afimdefeirax.SharedPreferences.HistoricoShared
import com.example.afimdefeirax.SharedPreferences.ListProdutosShared
import com.example.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.example.afimdefeirax.Utils.Monitoring
import com.example.afimdefeirax.ViewModel.ProdutosViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ProdutoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var viewModel: ProdutosViewModel


    @Mock
    private lateinit var mockProdutosShared: ListProdutosShared

    @Mock
    private lateinit var mockHistoricoShared: HistoricoShared

    @Mock
    private lateinit var mockAnalytics: FirebaseAnalyticsImpl

    @Mock
    private lateinit var mockApplication: Application

    @Mock
    private lateinit var mockContext: Context


    @Before
    fun setup() {

        MockitoAnnotations.openMocks(this)
        Dispatchers.resetMain()
        Dispatchers.setMain(testDispatcher)

        `when`(mockApplication.applicationContext).thenReturn(mockContext)

        viewModel = ProdutosViewModel(
            application = mockApplication,
            produtosshared = mockProdutosShared,
            historicoshared = mockHistoricoShared,
            firebaseAnalytics = mockAnalytics
        )


    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun takeProduts_should_add_product_to_list_and_save() = runTest {

        // Given
        val productimage = 1
        val productname = "Produto Teste"
        val product = Produtos(productimage, productname)

        val mockListProdutos = listOf(
            Produtos(productimage, productname)
        )

        // When
        viewModel.takeProduts(productimage, productname)
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertEquals(1, viewModel.listprodutos.size)
        assertEquals(product, viewModel.listprodutos[0])
        Mockito.verify(mockProdutosShared, times(1)).saveItems(mockContext, mockListProdutos)
    }


    @Test
    fun saveProducts_should_save_products() = runTest {

        // Given
        val productList = listOf(Produtos(1, "Produto 1"), Produtos(2, "Produto 2"))
        doNothing().`when`(mockProdutosShared).saveItems(mockContext, productList)

        //When
        viewModel.takeProduts(1, "Produto 1")
        viewModel.takeProduts(2, "Produto 2")
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        verify(mockProdutosShared, times(2)).saveItems(mockContext, productList)
    }

    @Test
    fun `takeProduts should log error when exception occurs`() = runTest {
        // Arrange
        val productimage = 1
        val productname = "Produto Teste"
        val exception = RuntimeException("Erro ao adicionar produto")
        val mockListProdutos = listOf(
            Produtos(productimage, productname)
        )

        //When
        `when`(mockProdutosShared.saveItems(mockContext, mockListProdutos)).thenThrow(exception)
        viewModel.takeProduts(productimage, productname)
        testDispatcher.scheduler.advanceUntilIdle()

        //Then
        verify(mockAnalytics, times(1)).firebaselogEvent(Monitoring.Product.PRODUCT_SAVE_ERROR)
    }

}





