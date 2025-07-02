package com.tops.retrofitdemo

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private const val TAG = "MyFirebaseMessagingServ"
class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.i(TAG, message.toString())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i(TAG, token)
    }
}