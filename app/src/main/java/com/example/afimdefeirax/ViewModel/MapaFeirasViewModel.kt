package com.example.afimdefeirax.ViewModel

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.ContextWrapper
import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afimdefeirax.R
import com.example.afimdefeirax.Repository.FeiraRepository.FeirasRepositoryImpl
import com.example.afimdefeirax.SharedPreferences.MapTutorialSharedImpl
import com.example.afimdefeirax.State.MapFeirasUIState
import com.example.afimdefeirax.Utils.FirebaseAnalytics.FirebaseAnalyticsImpl
import com.example.afimdefeirax.Utils.FocusCamera
import com.example.afimdefeirax.Utils.Location.LocationImpl
import com.example.afimdefeirax.Utils.Monitoring
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Objects


class MapaFeirasViewModel(
    private val application: Application,
    private var locationProvider: LocationImpl,
    private var camera: FocusCamera,
    private var feirasRepository: FeirasRepositoryImpl,
    private val analyticservice: FirebaseAnalyticsImpl,
    private val tutorialPreferences : MapTutorialSharedImpl
) : ViewModel() {

    private val _state: MutableStateFlow<MapFeirasUIState> = MutableStateFlow(MapFeirasUIState())
    val state: MutableStateFlow<MapFeirasUIState> = _state

    private lateinit var googleMap: GoogleMap
    private lateinit var userLocation: LatLng

    init {
        val tutorialAlreadyCompleted = tutorialPreferences.hasMapTutorialBeenCompleted()
        _state.update { currentState ->
            currentState.copy(
                selectedCity = "SAO PAULO",
                cityImages = R.mipmap.ic_bandeira_saopaulo,
                searchQuery = "",
                showBottomSheet = false,
                neighborhoodsToShow = application.resources.getStringArray(R.array.sp_bairros).toList(),
                showTutorial = !tutorialAlreadyCompleted
            )
        }
    }

    val cities = application.resources.getStringArray(R.array.cidades)

    val neighborhoodsMap: Map<String, List<String>> by lazy {
        mapOf(
            "SAO PAULO" to application.resources.getStringArray(R.array.sp_bairros).toList(),
            "GUARULHOS" to application.resources.getStringArray(R.array.guaru_bairros).toList(),
            "SUZANO" to application.resources.getStringArray(R.array.suza_bairros).toList(),
            "OSASCO" to application.resources.getStringArray(R.array.osasco_bairros).toList(),
            "MAUA" to application.resources.getStringArray(R.array.maua_bairros).toList(),
            "SANTO ANDRE" to application.resources.getStringArray(R.array.andre_bairros).toList(),
            "SAO BERNADO" to application.resources.getStringArray(R.array.bernado_bairros).toList(),
            "SAO CAETANO" to application.resources.getStringArray(R.array.caetano_bairros).toList()
        )
    }

    fun onShowTutorial(){
        if(!tutorialPreferences.hasMapTutorialBeenCompleted()){
            _state.update { it.copy(showTutorial = true) }
        }
    }

    fun onTutorialCompleted() {
        _state.update { it.copy(showTutorial = false) }
        tutorialPreferences.setMapTutorialCompleted(true)
    }

    fun onCityChange(newCity: String) {
        _state.update { currentState ->
            currentState.copy(selectedCity = newCity)
        }

    }

    fun onCityImageChange(newImage: Int) {
        _state.update { currentState ->
            currentState.copy(cityImages = newImage)
        }
    }

    fun onSearchQueryChange(newQuery: String) {
        _state.update { currentState ->
            currentState.copy(searchQuery = newQuery)
        }

    }

    fun toggleBottomSheet() {
        _state.update { currentState ->
            currentState.copy(showBottomSheet = !currentState.showBottomSheet)
        }
    }

    fun onNeighborhoodSelected(neighborhood: List<String>) {

        _state.update { currentState ->
            currentState.copy(neighborhoodsToShow = neighborhood)
        }

    }

    fun showMyLocalizationIn(map: GoogleMap) {
        analyticservice.firebaselogEvent(Monitoring.Map.MAP_MARKER_LOCALIZATION)

        val activityContext = when (application) {
            is Activity -> application
            is ContextWrapper -> application.baseContext as? Activity
            else -> null
        }
        viewModelScope.launch {

            try{
                locationProvider.getLastLocation { location ->
                    location?.let {
                        userLocation = LatLng(it.latitude, it.longitude)
                        camera.focusCamera(userLocation, map)
                        map.addMarker(
                            MarkerOptions()
                                .position(userLocation)
                                .title(application.getString(R.string.user_point))
                        )
                    }
                }
                activityContext?.let {
                    ActivityCompat.requestPermissions(
                        it,
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ),
                        200
                    )
                }
            }catch (error : Exception){
                analyticservice.firebaselogEvent(Monitoring.Map.MAP_ERROR)
                Log.e(Monitoring.Map.MAP_ERROR, error.message.toString())
                _state.update{currentState ->
                    currentState.copy(errorMessage = "Erro ao carregar mapa")
                }
            }

        }

    }


    fun showFeirasLocalizationIn(map: GoogleMap) {
        analyticservice.firebaselogEvent(Monitoring.Map.MAP_MARKER_FEIRAS)
        viewModelScope.launch {

            feirasRepository.getFeirasLocations { feiras ->
                feiras.forEach { feira ->
                    val latLng = LatLng(feira.Latitude.toDouble(), feira.Longitude.toDouble())
                    map.addMarker(
                        MarkerOptions()
                            .position(latLng)
                            .title(feira.Feira)
                            .snippet("${feira.endereco},${feira.bairro}")
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_loc))
                    )
                }
            }
        }
    }
    fun googleMap(map: GoogleMap) {

        googleMap = map
    }


    fun geoLocalization(cidade: String, bairro: String) {
        val local = "$cidade,$bairro"
        viewModelScope.launch{
            try {
                val gc = Geocoder(application.applicationContext)
                var list: List<Address?>? = null
                list = gc.getFromLocationName(local, 1)

                val localization = Objects.requireNonNull<List<Address>>(list as List<Address>?)[0]

                googleMap.animateCamera(
                    CameraUpdateFactory.newCameraPosition(camera.focusCamera(localization))
                )

            } catch (error: IOException) {
                analyticservice.firebaselogEvent(Monitoring.Map.MAP_SHOW_NEIGHBOOR_ERROR_)
                Log.e(Monitoring.Map.MAP_SHOW_NEIGHBOOR_ERROR_,error.message.toString())            }
        }
    }


}