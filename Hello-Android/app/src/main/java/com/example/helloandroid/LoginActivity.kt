package com.example.helloandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.helloandroid.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

//    lateinit var etUserName: EditText
//    lateinit var etPassword: EditText
//    lateinit var btnSubmit: Button
//    lateinit var btnRegister: Button
//    lateinit var tvError: TextView

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_login)


        title = getString(R.string.login_here)

//        etUserName = findViewById(R.id.etUserName)
//        etPassword = findViewById(R.id.etPassword)
//        btnSubmit = findViewById(R.id.btnSubmit)
//        btnRegister = findViewById(R.id.btnRegister)
//        tvError = findViewById(R.id.tvError)

        binding.btnSubmit.setOnClickListener {
            binding.tvError.text = ""
            val userName = binding.etUserName.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            if(userName == "Admin" && password == "Admin123") {
                // MainActivity redirect
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                // show error
                binding.tvError.text = "Invalid Credentials!!!"
            }
        }

    }
}