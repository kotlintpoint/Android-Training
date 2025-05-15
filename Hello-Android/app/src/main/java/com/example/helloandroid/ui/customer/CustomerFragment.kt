package com.example.helloandroid.ui.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.helloandroid.R

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer, container, false)
    }

}