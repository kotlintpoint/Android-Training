package com.tops.broadcastreceiverdemo

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.broadcastreceiverdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val receiver = MyReceiver()
        val filter = IntentFilter("com.tops.broadcastreceiverdemo.ACTION_UPDATE_DATA")
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)

        ContextCompat.registerReceiver(applicationContext, receiver, filter, ContextCompat.RECEIVER_NOT_EXPORTED)

        binding.btnSend.setOnClickListener {
            val newData = "Main Activity Broadcast"
            val intent = Intent("com.tops.broadcastreceiverdemo.ACTION_UPDATE_DATA").apply {
                putExtra("com.tops.broadcastreceiverdemo.DATA", newData)
                setPackage("com.tops.broadcastreceiverdemo")
            }
            applicationContext.sendBroadcast(intent)
        }
    }
}