package com.texnopos.uz.instagramtexnopos.ui.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.texnopos.uz.instagramtexnopos.data.helpers.AuthHelper
import com.texnopos.uz.instagramtexnopos.data.Resource

class SignInViewModel(private val authHelper: AuthHelper) : ViewModel() {
    private var mutableSingInStatus: MutableLiveData<Resource<String?>> = MutableLiveData()
    val signInStatus: LiveData<Resource<String?>>
        get() = mutableSingInStatus

    fun signIn(email: String, password: String) {
        mutableSingInStatus.value = Resource.loading()
        authHelper.signIn(email, password, {
            mutableSingInStatus.value = Resource.success(null)
        },
            {
                mutableSingInStatus.value = Resource.error(it)
            })
    }

}