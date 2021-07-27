package com.texnopos.uz.instagramtexnopos.ui.account.edit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.texnopos.uz.instagramtexnopos.R
import com.texnopos.uz.instagramtexnopos.data.model.User
import com.texnopos.uz.instagramtexnopos.databinding.FragmmentProfileEditBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.texnopos.uz.instagramtexnopos.data.ResourceState

class FragmentEdit : Fragment(R.layout.fragmment_profile_edit) {
    private lateinit var binding: FragmmentProfileEditBinding
    private val viewModel: EditViewModel by viewModel()
    private lateinit var navController: NavController
    private lateinit var cUser: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCurrentUser()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmmentProfileEditBinding.bind(view)
        navController = findNavController()
        setUpObservers()
        binding.apply {
            cancelBTN.setOnClickListener {
                navController.popBackStack()
            }
            doneBTN.setOnClickListener {
                navController.popBackStack()
                cUser.biography = etBiography.text.toString()
                cUser.name = edUser.text.toString()
                viewModel.editProfile(cUser)
            }
        }
    }
    private fun setUpObservers() {
        viewModel.user.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.LOADING -> {
                    setLoading(true)
                }
                ResourceState.SUCCESS -> {
                    binding.apply {
                        setLoading(false)
                        cUser = it.data!!
                        Glide.with(this@FragmentEdit)
                            .load(cUser.image)
                            .into(userImage)
                        edUser.setText(cUser.name)
                        etBiography.setText(cUser.biography)
                    }
                }
                ResourceState.ERROR -> {
                    binding.loading.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun setLoading(isLoading: Boolean) {
        binding.apply {
            loading.isVisible = isLoading
            cancelBTN.isEnabled = !isLoading
            doneBTN.isEnabled = !isLoading
            etBiography.isEnabled = !isLoading
            edUserName.isEnabled = !isLoading
        }
    }
}