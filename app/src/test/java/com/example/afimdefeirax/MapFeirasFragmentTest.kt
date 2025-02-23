package com.example.afimdefeirax

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.afimdefeirax.Utils.Location.LocationImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.model.LatLng
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@RunWith(AndroidJUnit4::class)
class MapFeirasFragmentTest {

    @Mock
     lateinit var mockFusedLocationClient :FusedLocationProviderClient

    @Mock
     lateinit var locationProvider: LocationImpl

     @Mock
    lateinit var  mockLocation : android.location.Location

    @Mock
    lateinit var mockPosition: LatLng

    val result= 0.0

    val latitude = 20.0
    val longitude = 10.0

    @Before
    fun setup() {

        mockFusedLocationClient = mockk<FusedLocationProviderClient>()
        locationProvider = LocationImpl(mockFusedLocationClient)
        mockLocation = mockk<android.location.Location>()
        mockPosition = mockk<LatLng>()


        MockKAnnotations.init(this)


    }

    @Test
    fun `given_the_last_location `() {

        every { mockLocation.latitude } returns latitude
        every { mockLocation.longitude } returns longitude



    }


    @Test
    fun `when_the_last_location_is_called `() {

//        every { mockFusedLocationClient.lastLocation } returns (mockLocation)

        runBlocking {
            locationProvider.getLastLocation {
                if (it != null) {
                    mockPosition = it
                }
            }
        }
    }

    @Test
    fun `then_the_last_location_is_returned `() {

        Assert.assertEquals(20.0, mockPosition.latitude,0.0000000000000001)
        Assert.assertEquals(10.0, mockPosition.longitude,0.0000000000000001)
    }


}