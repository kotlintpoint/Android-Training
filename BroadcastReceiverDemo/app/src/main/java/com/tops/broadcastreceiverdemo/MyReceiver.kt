package com.tops.broadcastreceiverdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

private const val TAG = "MyReceiver"
class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        if (intent.action == "com.tops.broadcastreceiverdemo.ACTION_UPDATE_DATA") {
            val data = intent.getStringExtra("com.tops.broadcastreceiverdemo.DATA") ?: "No data"
            // Do something with the data, for example send it to a data repository:
            //dataRepository.updateData(data)
            Log.i(TAG, data);
        }else if(intent.action ==  Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            val isAirplaneModeOn = intent.getBooleanExtra("state", false)
            Log.i(TAG, "Airplane mode changed $isAirplaneModeOn")
        }
        Log.i(TAG, "My Receiver")
    }
}