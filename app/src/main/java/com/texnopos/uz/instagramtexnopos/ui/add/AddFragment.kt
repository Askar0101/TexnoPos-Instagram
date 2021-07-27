package com.texnopos.uz.instagramtexnopos.ui.add

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.texnopos.uz.instagramtexnopos.R
import com.texnopos.uz.instagramtexnopos.data.ResourceState
import com.texnopos.uz.instagramtexnopos.databinding.FragmentAddBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream

class AddFragment : Fragment(R.layout.fragment_add) {
    companion object {
        const val IMAGE_CODE_PICK = 1000
    }

    private val viewModel: AddFragmentViewModel by viewModel()
    private lateinit var binding: FragmentAddBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)
        navController = findNavController()
        imageGallery()
        binding.apply {
            BtnSent.setOnClickListener {
                Toast.makeText(requireContext(), "Basildi", Toast.LENGTH_SHORT).show()
                ivPost.isDrawingCacheEnabled = true
                ivPost.buildDrawingCache()
                val bitmap = (ivPost.drawable as BitmapDrawable).bitmap
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                val data = baos.toByteArray()
                viewModel.getPostData(data, editTextView.text.toString())
            }
        }
        setUpservers()
    }

    private fun setUpservers() {
        viewModel.post.observe(viewLifecycleOwner,  {
            when (it.status) {
                ResourceState.LOADING -> {
                    binding.loading.isVisible = true
                }
                ResourceState.SUCCESS -> {
                    binding.loading.isVisible = false
                    navController.navigate(R.id.action_addFragment_to_homeFragment)

                }
                ResourceState.ERROR -> {
                    binding.loading.isVisible = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun imageGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_CODE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Activity.RESULT_OK && resultCode == IMAGE_CODE_PICK) {
            binding.ivPost.setImageURI(data?.data)
        }
    }
}