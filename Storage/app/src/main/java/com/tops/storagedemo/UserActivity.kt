package com.tops.storagedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.storagedemo.adapter.UserAdapter
import com.tops.storagedemo.databinding.ActivityMainBinding
import com.tops.storagedemo.databinding.ActivityUserBinding
import com.tops.storagedemo.entities.User

private const val TAG = "UserActivity"
class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAddUser.setOnClickListener {
            startActivity(
                Intent(this, NewUserActivity::class.java)
            )
        }

//        val dao = MyApplication.database.userDao()
//        var users = dao.getAll()
//        Log.i(TAG, users.toString())

        binding.userRecyclerView.layoutManager= LinearLayoutManager(this)
//        binding.userRecyclerView.adapter = UserAdapter(users, object : UserAdapter.OnUserClickListener {
//            override fun onUserEdit(user: User) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onUserDelete(user: User) {
//                dao.delete(user)
//                users = dao.getAll()
//                binding.userRecyclerView.adapter?.notifyDataSetChanged()
//            }
//        })
    }

    override fun onResume() {
        super.onResume()
        val dao = MyApplication.database.userDao()
        var users: ArrayList<User> = ArrayList(dao.getAll())
        binding.userRecyclerView.adapter = UserAdapter(users, object : UserAdapter.OnUserClickListener {
            override fun onUserEdit(user: User) {
                val intent = Intent(applicationContext, NewUserActivity::class.java)
                intent.putExtra("user", user)
                startActivity(intent)
            }

            override fun onUserDelete(user: User) {
                dao.delete(user) // remove from database
                users.remove(user) // remove from array list
                binding.userRecyclerView.adapter?.notifyDataSetChanged()
            }
        })
    }
}