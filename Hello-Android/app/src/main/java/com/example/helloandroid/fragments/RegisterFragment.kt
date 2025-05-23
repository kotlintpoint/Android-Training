package com.example.helloandroid.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.helloandroid.DrawerDashboardActivity
import com.example.helloandroid.R
import com.example.helloandroid.databinding.FragmentRegisterBinding
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = getString(R.string.register_here)

        binding.etPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().length < 10) {
                    binding.layoutPhone.error = "Invalid!!!"
                }else {
                    binding.layoutPhone.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.rgGender.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rbMale -> binding.layoutAge.visibility = View.VISIBLE
                R.id.rbFemale -> binding.layoutAge.visibility = View.GONE
            }
        }

        binding.cbTerms.setOnCheckedChangeListener { buttonView, isChecked ->
            binding.btnSubmit.isEnabled = isChecked
        }

        binding.btnSubmit.setOnClickListener {
            if(isFormValid()){
                val intent = Intent(context, DrawerDashboardActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun isFormValid(): Boolean {
        var isValid = true
        if(binding.etFirstName.text.toString().isEmpty()){
            isValid = false
            binding.layoutFirstName.error = "Required!!!"
        }else{
            binding.layoutFirstName.error = null
        }

        if(binding.etLastName.text.toString().isEmpty()){
            isValid = false
            binding.layoutLastName.error = "Required!!!"
        }else{
            binding.layoutLastName.error = null
        }

        if(binding.etEmail.text.toString().isEmpty()){
            isValid = false
            binding.layoutEmail.error = "Required!!!"
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()) {
            isValid = false
            binding.layoutEmail.error = "Invalid Email!!!"
        } else {
            binding.layoutEmail.error = null
        }
        return isValid
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment RegisterFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            RegisterFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}