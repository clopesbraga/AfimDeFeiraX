package com.example.afimdefeirax

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.afimdefeirax.Model.Historico
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.eq


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
    fun removeProducts_should_remove_produtcs()=runTest{

        //Given
        val product = Produtos(321,"Produto1")

        //When
        viewModel.removeProduct(product)

        //Then
        verify(mockProdutosShared).removeItem(mockContext,product)

    }

    @Test
    fun loadProducts_should_brings_list_of_products() =runTest{

        //Given
        val productList = listOf(Produtos(1, "Produto 1"), Produtos(2, "Produto 2"))
        `when`(mockProdutosShared.loadItems(mockContext)).thenReturn(productList)

        //When
        viewModel.loadProducts()
        testDispatcher.scheduler.advanceUntilIdle()

        //Then
        verify(mockProdutosShared,times(1)).loadItems(mockContext)

    }

    @Test
    fun requestOfHistoric_should_send_items_for_HistoricoScreen()=runTest{

        // Given
        val  feira = "Feira do Teste"
        val price = "5.00"
        val image = 171

       //When
        viewModel.requestOfHistorico(feira, price, image)
        testDispatcher.scheduler.advanceUntilIdle()


        //Then
        val captor = argumentCaptor<List<Historico>>()
        verify(mockHistoricoShared, times(1)).saveItems(eq(mockContext), captor.capture())
        val historicoSalvo = captor.firstValue

        assert(historicoSalvo.any {
            it.nome == feira && it.preco == price && it.imagem == image
        })

    }

    @Test
    fun `takeProduts should log error when exception occurs`() = runTest {
        // Arrange
        val productimage = 1
        val productname = "Produto Teste"
        val exception = RuntimeException("Erro ao adicionar produto")

        //When
        val productList = listOf(Produtos(1, "Produto 1"), Produtos(2, "Produto 2"))

        doThrow(exception).`when`(mockProdutosShared).saveItems(mockContext, productList)


        viewModel.takeProduts(productimage, productname)
        testDispatcher.scheduler.advanceUntilIdle()


        //Then
        verify(mockAnalytics, times(1)).firebaselogEvent(Monitoring.Product.PRODUCT_SAVE_ERROR)
    }


}





