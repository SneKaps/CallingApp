package com.example.callingapp.firebaseClient

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class FirebaseClient(
    private val dbReference: DatabaseReference = FirebaseDatabase.getInstance("https://callingapp-1a2cf-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
) {
    suspend fun markUserOnline(phoneNumber: String?) {
        phoneNumber ?: return

        try {
            dbReference.child(phoneNumber).child("status").setValue("online").await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}