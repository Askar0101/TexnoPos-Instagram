package com.texnopos.uz.instagramtexnopos.ui.auth.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.texnopos.uz.instagramtexnopos.R
import com.texnopos.uz.instagramtexnopos.databinding.FragmentSigninBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.texnopos.uz.instagramtexnopos.data.ResourceState

class SignInFragment:Fragment(R.layout.fragment_signin) {
    private lateinit var binding: FragmentSigninBinding
    private val viewModel:SignInViewModel by viewModel()
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentSigninBinding.bind(view)
        navController = Navigation.findNavController(view)
        setObservers()
        binding.apply {
           btnSignUp.setOnClickListener {
                navController.navigate(R.id.action_signinFragment_to_signUpFragment)

            }
           btnSignIn.setOnClickListener {
                var success=true
                if (editEmal.text.isNullOrEmpty()){
                    success=false
                    editEmal.error = getString(R.string.fill_the_field)

                }
               if (editPasswort.text.isNullOrEmpty()){
                   success=false
                   editPasswort.error=getString(R.string.fill_the_field)
               }
               if (!success)return@setOnClickListener
               viewModel.signIn(editEmal.text.toString(),editPasswort.text.toString())
            }
        }

    }
    private fun setObservers(){
        viewModel.signInStatus.observe(viewLifecycleOwner, {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    navController.navigate(R.id.action_signinFragment_to_mainFragment)
                }
                ResourceState.ERROR -> {
                    setLoading(false)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    private fun setLoading(isLoading:Boolean){
        binding.apply {
            loading.isVisible=isLoading
            editEmal.isEnabled=!isLoading
            editPasswort.isEnabled=!isLoading

        }   }
}