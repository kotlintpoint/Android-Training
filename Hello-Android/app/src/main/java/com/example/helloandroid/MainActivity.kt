package com.example.helloandroid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.helloandroid.fragments.LoginFragment

val IS_LOGIN = "IS_LOGIN"

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        title = getString(R.string.dashboard)

//        supportFragmentManager
//            .beginTransaction()
//            .add(R.id.fragmentContainerView, LoginFragment())
//            .commit()


        val sharedPref = getSharedPreferences(
            getString(R.string.app_name), Context.MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean(IS_LOGIN, false)

        if(isLogin){
            val intent = Intent(this, DrawerDashboardActivity::class.java)
            startActivity(intent)
            finish()
        }else {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<LoginFragment>(R.id.fragmentContainerView)
            }
        }
    }
}