package com.tops.viewmodeldemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.viewmodeldemo.databinding.ActivityUserBinding
import com.tops.viewmodeldemo.model.User
import com.tops.viewmodeldemo.viewModels.UserViewModel

class UserActivity : AppCompatActivity() {
    lateinit var binding : ActivityUserBinding
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityUserBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.userList.layoutManager = LinearLayoutManager(this)
        binding.userList.adapter = UserAdapter(emptyList())

        userViewModel.users.observe(this, Observer {
            binding.userList.adapter = UserAdapter(it)
        })

        binding.btnAdd.setOnClickListener {
            val user = User(
                binding.etId.text.toString().toInt(),
                binding.etName.text.toString()
            )
            userViewModel.addUser(user)
        }

    }
}