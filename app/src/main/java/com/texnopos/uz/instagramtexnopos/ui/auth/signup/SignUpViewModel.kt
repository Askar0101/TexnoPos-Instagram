package com.texnopos.uz.instagramtexnopos.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.texnopos.uz.instagramtexnopos.data.helpers.AuthHelper
import com.texnopos.uz.instagramtexnopos.data.Resource

class SignUpViewModel(private val authHelper: AuthHelper) : ViewModel() {
    private var mutableSingUpUser: MutableLiveData<Resource<String?>> = MutableLiveData()
    val signUpStatus: LiveData<Resource<String?>>
        get() = mutableSingUpUser

    fun signUp(email: String, password: String) {
        mutableSingUpUser.value = Resource.loading()
        authHelper.signUp(email, password,
            {
                addUserToDb()
            },
            {
                mutableSingUpUser.value = Resource.error(it)
            })
    }

    private fun addUserToDb() {
        authHelper.addUserToDb(
            {
                mutableSingUpUser.value = Resource.success(null)
            },
            {
                mutableSingUpUser.value = Resource.error(it)
            }
        )
    }
}