package com.texnopos.uz.instagramtexnopos.ui.auth.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.texnopos.uz.instagramtexnopos.R
import com.texnopos.uz.instagramtexnopos.databinding.FragmentSignUpBinding

class SignUpFragment:Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignUpBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentSignUpBinding.bind(view)
        binding.btnSignUp.setOnClickListener {
            if (binding.editEmal.text.isNullOrEmpty()){
                binding.editEmal.error=getString(R.string.fill_the_field)

            }
        }
    }
}