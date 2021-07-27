package com.texnopos.uz.instagramtexnopos.ui.account

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.texnopos.uz.instagramtexnopos.R
import com.texnopos.uz.instagramtexnopos.databinding.FragmentAccountBinding
import com.texnopos.uz.instagramtexnopos.ui.main.MainFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.texnopos.uz.instagramtexnopos.data.ResourceState

class AccountFragment : Fragment(R.layout.fragment_account) {
    private val viewModel: AccountViewModel by viewModel()
    private lateinit var binding: FragmentAccountBinding
     private lateinit var parentNavController:NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAccountBinding.bind(view)
        setUpObservers()
        viewModel.getProfileData()
        parentNavController = (parentFragment?.parentFragment as MainFragment).findNavController()
        binding.btnEdit.setOnClickListener {
            parentNavController.navigate(R.id.action_mainFragment_to_fragmentEdit)
        }
    }
    private fun setUpObservers(){
        viewModel.profile.observe(viewLifecycleOwner,{
            when(it.status){
                ResourceState.LOADING->{
                    setLoading(true)
                }
                ResourceState.SUCCESS->{
                    setLoading(false)
                    binding.apply {
                        val u=it.data!!
                        name.text=u.email
                        tvBiography.text=u.biography
                        tvTitil.text=u.name
                        tvfollowsConut.text=u.followesrCount.toString()
                        tvPostConut.text=u.postCount.toString()
                        tvfollowConut.text=u.followingsCount.toString()
                        Glide.with(this@AccountFragment)
                            .load(u.image)
                            .centerCrop()
                            .into(avatar)

                    }
                }
            }
        })
    }
    private fun setLoading(isLoading:Boolean){
        binding.apply {
            loading.isVisible=isLoading
            btnEdit.isEnabled=!isLoading
            rv.isEnabled=!isLoading
            name.isEnabled=!isLoading
        }
    }
}