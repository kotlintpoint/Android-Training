package com.tops.storagedemo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.tops.storagedemo.databinding.ActivityNewUserBinding
import com.tops.storagedemo.db.AppDatabase
import com.tops.storagedemo.entities.User

private const val TAG = "NewUserActivity"

class NewUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        val db: AppDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name")
//            .allowMainThreadQueries().build()
//        val dao = db.userDao()

        val dao = MyApplication.database.userDao()

        binding.btnSubmit.setOnClickListener {
            val user = User(null, binding.etFirstName.text.toString(), binding.etLastName.text.toString())
            dao.insertAll(user)
            Log.i(TAG, "Inserted!!!")
        }

    }
}