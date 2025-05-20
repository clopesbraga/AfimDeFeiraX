package com.example.afimdefeirax.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.afimdefeirax.R
import com.example.afimdefeirax.State.MainUIState
import com.example.afimdefeirax.View.MainScreen
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlinx.coroutines.flow.update


class MainViewModel : ViewModel() {

    private var mInterstitialAd: InterstitialAd? = null
    private val TAG = "MainActivity"

    private val _state: MutableStateFlow<MainUIState> = MutableStateFlow(MainUIState())
    val state: StateFlow<MainUIState> = _state

    fun onNavigationCount(count:Int) {
        _state.update { currentState ->
            currentState.copy(navigatecount = count)
        }

    }

    fun loadAds(context: Context) {
        val adRequest = AdRequest.Builder().build()
        val adUnitId = context.getString(R.string.admob_interstitial_ad_unit_id)

        InterstitialAd.load(
            context,
            adUnitId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.toString().toString())
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })
    }


    fun ShowAd(context: Context) {

        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdClicked() {
                    // Called when a click is recorded for an ad.
//                    Log.d(com.example.testanunciosapp.components.TAG, "Ad was clicked.")
                }

                override fun onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
//                    Log.d(com.example.testanunciosapp.components.TAG, "Ad dismissed fullscreen content.")
                    loadAds(context)
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    // Called when ad fails to show.
//                    Log.e(com.example.testanunciosapp.components.TAG, "Ad failed to show fullscreen content.")
                    loadAds(context)
                }

                override fun onAdImpression() {
                    // Called when an impression is recorded for an ad.
//                    Log.d(com.example.testanunciosapp.components.TAG, "Ad recorded an impression.")
                }

                override fun onAdShowedFullScreenContent() {
                    // Called when ad is shown.
//                    Log.d(com.example.testanunciosapp.components.TAG, "Ad showed fullscreen content.")
                }

            }
            mInterstitialAd?.show(context as MainScreen)

        } else {
            loadAds(context)
        }

    }

}