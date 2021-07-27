package com.texnopos.uz.instagramtexnopos.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.texnopos.uz.instagramtexnopos.data.helpers.ProfileHelper
import com.texnopos.uz.instagramtexnopos.data.model.User
import com.texnopos.uz.instagramtexnopos.data.Resource

class AccountViewModel(private val profileHelper: ProfileHelper) : ViewModel() {
    private var mutableProfile: MutableLiveData<Resource<User>> = MutableLiveData()
    val profile: LiveData<Resource<User>>
        get() = mutableProfile

    fun getProfileData() {
        mutableProfile.value = Resource.loading()
            profileHelper.getProfileData(
                {
                    mutableProfile.value = Resource.success(it)
                }, {
                    mutableProfile.value = Resource.error(it)
                }
            )
    }
}