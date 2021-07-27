package com.texnopos.uz.instagramtexnopos.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.texnopos.uz.instagramtexnopos.data.Resource
import com.texnopos.uz.instagramtexnopos.data.helpers.PostHelper

class AddFragmentViewModel(private val posthelper: PostHelper) : ViewModel() {
    private var mutablePost: MutableLiveData<Resource<String?>> = MutableLiveData()
    val post: LiveData<Resource<String?>> get() = mutablePost
    fun getPostData(byteArray: ByteArray, description: String) {
        mutablePost.value = Resource.loading()
        posthelper.sendNewPost(byteArray, description,
            {
                mutablePost.value = Resource.success("success")
            }, {
                mutablePost.value = Resource.error(it)
            }
        )
    }
}