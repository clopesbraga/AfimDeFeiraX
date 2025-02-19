package com.example.afimdefeirax

import android.app.Application
import android.content.res.Resources
import android.location.LocationProvider
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.afimdefeirax.Repository.FeiraRepository.FeirasRepositoryImpl
import com.example.afimdefeirax.Repository.FeiraRepository.IFeiraRepository
import com.example.afimdefeirax.Utils.FocusCamera
import com.example.afimdefeirax.Utils.Location.LocationImpl
import com.example.afimdefeirax.ViewModel.MapaFeirasViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.robolectric.shadows.ShadowPackageManager.resources

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MapFeirasTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)



    private  lateinit var viewModel: MapaFeirasViewModel

    @Mock
    private lateinit var mockLocationProvider: LocationImpl

    @Mock
    private lateinit var mockUserLocation:LatLng

    @Mock
    private lateinit var mockFeirasRepository: FeirasRepositoryImpl

    @Mock
    private lateinit var mockGooglemap: GoogleMap

    @Mock
    private  lateinit var mockApplication: Application

    @Mock
    private lateinit var mockCamera: FocusCamera

    @Mock
    private lateinit var mockResources: Resources


    @Before
    fun setup(){

        MockitoAnnotations.openMocks(this)
        Dispatchers.resetMain()
        Dispatchers.setMain(testDispatcher)

        `when`(mockApplication.resources).thenReturn(mockResources)
        `when`(mockResources.getStringArray(R.array.cidades))
            .thenReturn(arrayOf("SAO PAULO", "GUARULHOS", "SUZANO"))
        `when` (mockResources.getStringArray(R.array.sp_bairros) )
            .thenReturn(arrayOf("Centro", "Zona Sul"))

        viewModel = MapaFeirasViewModel(
            application = mockApplication,
            locationProvider = mockLocationProvider,
            userLocation = mockUserLocation,
            feirasRepository = mockFeirasRepository,
            googleMap = mockGooglemap,
            camera = mockCamera
        )
    }

    @Test
    fun `test init state`() = runTest {
        val state = viewModel.state.first()
        assertEquals("SAO PAULO", state.selectedCity)
        assertEquals(R.mipmap.ic_bandeira_saopaulo, state.cityImages)
        assertEquals(false, state.showBottomSheet)
        assertEquals(listOf("Centro", "Zona Sul"), state.neighborhoodsToShow)
    }

    @Test
    fun `When put a new city then its updated in state`()=runTest{
        //Given
        val newCity = "Test_city"

        //When
        viewModel.onCityChange(newCity)
        val state = viewModel.state.first()

        //Then
        assert(viewModel.state.value.selectedCity == newCity)
        assertEquals(newCity, state.selectedCity)

    }

    @Test
    fun `When put a new neighborhood  its selected then updated in state`()=runTest{

        //Given
        val newNeighborhood = listOf("Test_neighborhood")

        //When
        viewModel.onNeighborhoodSelected(newNeighborhood)
        val state = viewModel.state.first()

        //Then
        assert(viewModel.state.value.neighborhoodsToShow == newNeighborhood)
        assertEquals(newNeighborhood, state.neighborhoodsToShow)
    }


    @Test
    fun `When search query changes then its updated in state`()=runTest{

        //Given
        val newQuery = "Test_query"

        //When
        viewModel.onSearchQueryChange(newQuery)
        viewModel.state.first()

        //Then
        assert(viewModel.state.value.searchQuery == newQuery)

    }


    @Test
    fun `When call toggleBottomSheet then change for true in state `()=runTest{

        //Given
        val newToggleAction = true

        //When
        viewModel.toggleBottomSheet()

        //Then
        assert(viewModel.state.value.showBottomSheet==newToggleAction)

    }



}