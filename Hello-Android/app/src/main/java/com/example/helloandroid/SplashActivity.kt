package com.example.helloandroid

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler().postDelayed({
//            val intent = Intent(this, LoginActivity::class.java)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

        Log.i("SplashActivity", "onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.i("SplashActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("SplashActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("SplashActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("SplashActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("SplashActivity", "onDestroy")
    }
}