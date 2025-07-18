package com.tops.viewmodeldemo.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.tops.viewmodeldemo.R
import com.tops.viewmodeldemo.databinding.FragmentLoginBinding
import com.tops.viewmodeldemo.model.LoginResponse
import com.tops.viewmodeldemo.model.LoginUser
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
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private val TAG = "LoginFragment"
    private lateinit var service: AuthService

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

    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginResponse = SharedPreferenceService(requireActivity()).readLoginResponse()
        if(loginResponse != null){
            findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(AuthService::class.java)

        binding.btnSubmit.setOnClickListener {
           checkLogin()
        }
    }

    private fun checkLogin() {
        val loginUser = LoginUser(
            username = binding.etUserName.text.toString(),
            password = binding.etPassword.text.toString()
        )
        val call = service.login(loginUser)
        call.enqueue(object: Callback<LoginResponse>{
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if(response.isSuccessful){
                    // save data to sharePreference
                    SharedPreferenceService(requireActivity()).writeLoginResponse(response.body()!!)
                    findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i(TAG, t.toString())
            }

        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}