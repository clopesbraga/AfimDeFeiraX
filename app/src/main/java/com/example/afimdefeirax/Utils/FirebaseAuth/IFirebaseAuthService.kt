package com.example.afimdefeirax.Utils.FirebaseAuth

interface IFirebaseAuthService {

    suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean
    suspend fun createUserWithEmailAndPassword(email: String, password: String): Boolean
    fun getCurrentUserEmail(): String?

}
