package com.texnopos.uz.instagramtexnopos.ui.main

import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.texnopos.uz.instagramtexnopos.R
import com.texnopos.uz.instagramtexnopos.data.Settins
import com.texnopos.uz.instagramtexnopos.databinding.FragmentMainBinding
import org.koin.android.ext.android.inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private val settings: Settins by inject()
    private lateinit var navController: NavController
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settings.signedIn = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainer)
        binding.bnv.setupWithNavController(navController)
    }
}