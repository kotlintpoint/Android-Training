package com.tops.storagedemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.storagedemo.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val sharedPref = getSharedPreferences(
//            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return

        binding.btnWritePreference.setOnClickListener {
            with (sharedPref.edit()) {
                putString(getString(R.string.message), binding.etMessage.text.toString())
                apply()
            }
            Log.i(TAG, "Share Preference written")
        }

        binding.btnReadPreference.setOnClickListener {
            val message = sharedPref.getString(getString(R.string.message),"")
            binding.etMessage.setText(message)
        }
    }
}