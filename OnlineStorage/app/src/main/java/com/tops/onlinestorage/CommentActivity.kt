package com.tops.onlinestorage

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tops.onlinestorage.model.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request


private const val TAG = "CommentActivity"
class CommentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_comment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        lifecycleScope.launch {
            val commentsList = fetchComments()
            Log.i(TAG, "Total : ${commentsList?.size}")
        }
    }

    private suspend fun fetchComments(): ArrayList<Comment>? = withContext(Dispatchers.IO) {
        val client = OkHttpClient()

        val request: Request =
            Request.Builder().url("https://jsonplaceholder.typicode.com/comments")
                .build()

        val response = client.newCall(request).execute()
        if(response.isSuccessful){
            val jsonResponse = response.body!!.string()

            // Json Array Parsing using gson
            val commentList = Gson().fromJson<ArrayList<Comment>>(jsonResponse, object : TypeToken<ArrayList<Comment>>(){}.type)
            Log.i(TAG, commentList.toString())

//            jsonResponse.let {
//                val jsonArray = JSONArray(it)
//                val commentList = arrayListOf<Comment>()
//                for (i in 0..<jsonArray.length()){
//                    val jsonObject = jsonArray.getJSONObject(i)
//                    // json object parsing using gson
//                    val comment: Comment = Gson().fromJson(jsonObject.toString(), Comment::class.java)
//                    commentList.add(comment)
//                }
//                Log.i(TAG, commentList.toString())
//            }

            return@withContext commentList
        } else {
            return@withContext null
        }

    }
}