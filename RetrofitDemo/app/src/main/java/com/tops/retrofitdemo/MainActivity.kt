package com.tops.retrofitdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.tops.retrofitdemo.adapters.ProductAdapter
import com.tops.retrofitdemo.databinding.ActivityMainBinding
import com.tops.retrofitdemo.model.NewProductResponse
import com.tops.retrofitdemo.model.Product
import com.tops.retrofitdemo.model.ProductRoot
import com.tops.retrofitdemo.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productData: ArrayList<Product>
    private var productPosition: Int? = null

    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())  { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newProduct = result.data?.getParcelableExtra("product", NewProductResponse::class.java)
            val product = Product(
                id = newProduct!!.id,
                title = newProduct.title,
                description = newProduct.description
            )
            Toast.makeText(this, "Received: $product", Toast.LENGTH_SHORT).show()
            if(productPosition != null){
                // update
                productData.set(productPosition!!, product)
            }else{
                productData.add(0, product)
            }

            binding.recyclerView.adapter!!.notifyDataSetChanged()
        }
    }

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
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.product_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if(menuItem.itemId == R.id.action_new) {
                    val intent = Intent(applicationContext, NewProductActivity::class.java)
                    resultLauncher.launch(intent)
                    return true
                }else if(menuItem.itemId == R.id.action_logout){
                    Firebase.auth.signOut()
                    finish()
                    return true
                }
                return false;
            }
        })

        val call: Call<ProductRoot> = RetrofitClient.getInstance().listProducts()
        binding.progressBar.visibility = View.VISIBLE
        call.enqueue(object: Callback<ProductRoot> {
            override fun onResponse(call: Call<ProductRoot>, response: Response<ProductRoot>) {
                binding.progressBar.visibility = View.GONE
                if(response.isSuccessful){
                  val data = response.body()
                  Log.i(TAG, "Total Products : ${data?.total}")
//                  binding.tvData.setText(data?.products.toString())
                    bindProductDataAdapter(data)
                }
            }

            override fun onFailure(call: Call<ProductRoot>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Log.i(TAG, t.message!!)
            }
        })
    }

    private fun bindProductDataAdapter(data: ProductRoot?) {

        productData = (data?.products as ArrayList<Product>?)!!
        binding.recyclerView.adapter = ProductAdapter(productData, object: ProductAdapter.ProductClickListener{
            override fun onProductEdit(position: Int) {
                productPosition = position
                val intent = Intent(applicationContext, NewProductActivity::class.java)
                val bundle = Bundle().apply {
                    putParcelable("product", productData[position])
                }
                intent.putExtras(bundle)
                resultLauncher.launch(intent)

            }

            override fun onProductDelete(position: Int) {
                deleteProduct(position)
            }
        })
    }

    private fun deleteProduct(position: Int) {
        val call = RetrofitClient.getInstance().deleteProduct(productData[position].id!!)
        binding.progressBar.visibility = View.VISIBLE
        call.enqueue(object: Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                binding.progressBar.visibility = View.GONE
                if(response.isSuccessful){
                    Log.i(TAG, response.body().toString())
                    productData.removeAt(position)
                    binding.recyclerView.adapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                Log.i(TAG, t.message!!)
            }
        })
    }
}