package com.texnopos.uz.instagramtexnopos.ui.splash

import android.animation.Animator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.texnopos.uz.instagramtexnopos.R
import com.texnopos.uz.instagramtexnopos.Settins
import com.texnopos.uz.instagramtexnopos.databinding.FragmentSplashBinding

class SplashFragment:Fragment(R.layout.fragment_splash) {
    private lateinit var binding: FragmentSplashBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        requireActivity().actionBar?.hide()
        binding.splashview.setMaxProgress(0.6f)
        val settins=Settins(requireContext())
        binding.splashview.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                if (settins.signedIn){

                }else{

                }
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationStart(animation: Animator?) {

            }

        })
    }
}