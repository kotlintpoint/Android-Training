package com.tops.onlinestorage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.onlinestorage.adapter.ProductAdapter
import com.tops.onlinestorage.databinding.ActivityMainBinding
import com.tops.onlinestorage.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

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

        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = View.VISIBLE
                val result = getData()
                binding.progressBar.visibility = View.GONE
                Log.d("API_CALL", "Result: $result")
//                binding.tvData.setText(result.toString())

                binding.productRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
                binding.productRecyclerView.adapter = ProductAdapter(result!!)
                // update UI if needed
            } catch (e: Exception) {
                Log.e("API_CALL", "Error: ${e.message}")
            }
        }
    }

    private suspend fun getData():ArrayList<Product>? = withContext(Dispatchers.IO) {
        // Perform network operation here
        val client = OkHttpClient()

        val request: Request =
            Request.Builder().url("https://api.restful-api.dev/objects").build()

        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val jsonResponse = response.body!!.string()
            // JSON parsing
            jsonResponse.let {
                val jsonArray = JSONArray(it)
                val productList = arrayListOf<Product>()
                for (i in 0..<jsonArray.length()){
                    val jsonObject = jsonArray.getJSONObject(i)
                    val objectId = jsonObject.getString("id")
                    val objectName = jsonObject.getString("name")

                    // Complex or unknown property when you don't know key or dynamic key
                    val dataMap = mutableMapOf<String, Any>()
                    try{
                        val dataJsonObject = jsonObject.getJSONObject("data")
                        val iterator = dataJsonObject.keys()
                        while(iterator.hasNext()){
                            val key = iterator.next()
                            val value = dataJsonObject.get(key)
                            dataMap.put(key, value)
                        }
                    }  catch (e: Exception) {
                        Log.e("API_CALL", "Error: ${e.message}")
                    }

                    val product = Product(objectId, objectName, dataMap)
                    productList.add(product)
                }
                return@withContext productList
            }
        } else {
            null
        }
    }


}