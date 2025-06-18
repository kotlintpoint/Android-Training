package com.tops.retrofitdemo

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
import com.tops.retrofitdemo.databinding.ActivityNewProductBinding
import com.tops.retrofitdemo.model.NewProduct
import com.tops.retrofitdemo.model.NewProductResponse
import com.tops.retrofitdemo.model.Product
import com.tops.retrofitdemo.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "NewProductActivity"
class NewProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityNewProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbar)
        binding.btnSubmit.setOnClickListener {
            submitNewProduct()
        }
    }

    private fun submitNewProduct() {
        val product = NewProduct(
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString()
        )
        val call:Call<NewProductResponse> = RetrofitClient.getInstance().saveProduct(product)
        call.enqueue(object: Callback<NewProductResponse>{
            override fun onResponse(call: Call<NewProductResponse>, response: Response<NewProductResponse>) {
                Log.i(TAG, response.raw().toString())
                if(response.isSuccessful && response.raw().code == 201){
                    Log.i(TAG, response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<NewProductResponse>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

        })
    }
}