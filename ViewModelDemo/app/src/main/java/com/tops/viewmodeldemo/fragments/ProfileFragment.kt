package com.tops.viewmodeldemo.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tops.viewmodeldemo.R
import com.tops.viewmodeldemo.model.UserProfile
import com.tops.viewmodeldemo.services.AuthService
import com.tops.viewmodeldemo.services.SharedPreferenceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private lateinit var service: AuthService
    private val TAG = "ProfileFragment"

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(AuthService::class.java)
        fetchProfileDetails()
    }

    private fun fetchProfileDetails() {
        val loginResponse = SharedPreferenceService(requireActivity()).readLoginResponse()
        if(loginResponse == null){
            // navigate to loginFragment
        }
        else
        {
            val accessToken = loginResponse.accessToken
            Log.i(TAG, accessToken)
            val call = service.profile("Bearer $accessToken")
            call.enqueue(object: Callback<UserProfile>{
                override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                    if(response.isSuccessful){
                        Log.i(TAG, response.body().toString())
                    }
                }

                override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    Log.i(TAG, t.toString())
                }

            })
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}