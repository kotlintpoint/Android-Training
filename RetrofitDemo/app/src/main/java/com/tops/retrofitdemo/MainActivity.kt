package com.tops.retrofitdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.retrofitdemo.databinding.ActivityMainBinding
import com.tops.retrofitdemo.model.ProductRoot
import com.tops.retrofitdemo.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        setSupportActionBar(binding.toolbar)

        addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.product_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.action_new) {
                    startActivity(Intent(applicationContext, NewProductActivity::class.java))
                    return true
                }
                return false;
            }
        })

        val call: Call<ProductRoot> = RetrofitClient.getInstance().listProducts()
        call.enqueue(object: Callback<ProductRoot> {
            override fun onResponse(call: Call<ProductRoot>, response: Response<ProductRoot>) {
                if(response.isSuccessful){
                  val data = response.body()
                  Log.i(TAG, "Total Products : ${data?.total}")
                  binding.tvData.setText(data?.products.toString())
                }
            }

            override fun onFailure(call: Call<ProductRoot>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }
        })
    }
}