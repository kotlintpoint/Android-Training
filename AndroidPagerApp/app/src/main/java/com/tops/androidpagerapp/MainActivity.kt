package com.tops.androidpagerapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.tops.androidpagerapp.adapter.PagerAdapter
import com.tops.androidpagerapp.adapter.PagerAdapter2
import com.tops.androidpagerapp.databinding.ActivityMainBinding
import com.tops.androidpagerapp.model.PagerFragment
import com.tops.androidpagerapp.pages.FirstFragment
import com.tops.androidpagerapp.pages.SecondFragment
import com.tops.androidpagerapp.pages.ThirdFragment
import java.util.ArrayList


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


//        val fragmentList: ArrayList<Fragment> = arrayListOf(FirstFragment(), SecondFragment(), ThirdFragment())
        val fragmentList: ArrayList<PagerFragment> = arrayListOf(
            PagerFragment(FirstFragment(), "First"),
            PagerFragment(SecondFragment(), "Second"),
            PagerFragment(ThirdFragment(), "Third"),
//            PagerFragment(FirstFragment(), "First"),
//            PagerFragment(SecondFragment(), "Second"),
//            PagerFragment(ThirdFragment(), "Third"),
//            PagerFragment(FirstFragment(), "First"),
//            PagerFragment(SecondFragment(), "Second"),
//            PagerFragment(ThirdFragment(), "Third")
        )
//        val adapter = PagerAdapter(this, fragmentList)
        val adapter = PagerAdapter2(this, fragmentList)
        binding.viewpager.adapter = adapter

        TabLayoutMediator(binding.tabLayout,binding.viewpager){ tab, position ->
//            tab.text = "Page ${(position + 1)}"
            tab.text = fragmentList[position].title
            tab.icon = getDrawable(R.mipmap.ic_launcher)
        }.attach()

    }
}