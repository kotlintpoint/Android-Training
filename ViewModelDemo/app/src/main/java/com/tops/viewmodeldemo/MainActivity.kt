package com.tops.viewmodeldemo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.tops.viewmodeldemo.databinding.ActivityMainBinding
import com.tops.viewmodeldemo.viewModels.CountViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
//    private var count = 0
    private val countViewModel: CountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.textCount = "Count Data : 0"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        binding.tvCount.setText("Count : $count")
        countViewModel.count.observe(this, Observer<Int> {
            count ->  binding.textCount = "Count Data : $count"
        })

        binding.btnIncrement.setOnClickListener {
//            count = count + 1
//            binding.tvCount.setText("Count : $count")
            countViewModel.increment()
        }

        binding.btnDecrement.setOnClickListener {
//            count = count - 1
//            binding.tvCount.setText("Count : $count")
            countViewModel.decrement()
        }

        binding.btnReset.setOnClickListener {
//            count = 0
//            binding.tvCount.setText("Count : $count")
            countViewModel.reset()
        }
    }
}