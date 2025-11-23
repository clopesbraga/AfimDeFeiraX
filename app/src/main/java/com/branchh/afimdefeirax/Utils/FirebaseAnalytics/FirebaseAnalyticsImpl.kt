package com.branchh.afimdefeirax.Utils.FirebaseAnalytics

import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseAnalyticsImpl(private val analytics: FirebaseAnalytics): IFirebaseAnalytics {

    override fun firebaselogEvent(log: String) {

        analytics.logEvent(log,null)

    }

}