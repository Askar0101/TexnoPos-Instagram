package com.texnopos.uz.instagramtexnopos.ui.auth.signup

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.texnopos.uz.instagramtexnopos.R
import com.texnopos.uz.instagramtexnopos.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.texnopos.uz.instagramtexnopos.data.ResourceState

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModel()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding = FragmentSignUpBinding.bind(view)
        binding.apply {
            btnSignUp.setOnClickListener {
                var success = true
                if (editEmal.text.isNullOrEmpty()) {
                    editEmal.error = getString(R.string.fill_the_field)
                    success = false
                }
                if (editPasswort.text.isNullOrEmpty()) {
                    editPasswort.error = getString(R.string.fill_the_field)
                    success = false
                }
                if (editConfirmPasswort.text.isNullOrEmpty()) {
                    editConfirmPasswort.error = getString(R.string.fill_the_field)
                    success = false
                }
                if (!success) return@setOnClickListener
                if (editConfirmPasswort.text.toString() != editPasswort.text.toString()) {
                    success = true
                    editConfirmPasswort.error =
                        getString(R.string.passwords_do_not_match_each_other)
                } else {
                    viewModel.signUp(editEmal.text.toString(), editPasswort.text.toString())
                }
            }
        }
        setObservers()
    }

    private fun setObservers() {
        viewModel.signUpStatus.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    navController.navigate(R.id.action_signUpFragment_to_mainFragment)
                }
                ResourceState.ERROR -> {
                    setLoading(false)
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun setLoading(isLoading: Boolean) {
        binding.apply {
            loading.isVisible = isLoading
            editEmal.isEnabled = !isLoading
            editPasswort.isEnabled = !isLoading
            editConfirmPasswort.isEnabled = !isLoading
        }
    }
}

