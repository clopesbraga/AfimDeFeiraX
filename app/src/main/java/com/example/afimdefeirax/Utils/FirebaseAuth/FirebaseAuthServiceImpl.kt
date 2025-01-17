package com.example.afimdefeirax.Utils.FirebaseAuth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthServiceImpl(private val auth: FirebaseAuth) :IFirebaseAuthService{

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        auth.signInWithEmailAndPassword(email, password).await()
        return true
    }

    override suspend fun createUserWithEmailAndPassword(email: String, password: String): Boolean {
        auth.createUserWithEmailAndPassword(email, password).await()
        return true
    }

    override fun getCurrentUserEmail(): String? {
        return auth.currentUser?.email
    }


}