package com.texnopos.uz.instagramtexnopos.ui.account.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.texnopos.uz.instagramtexnopos.data.helpers.ProfileHelper
import com.texnopos.uz.instagramtexnopos.data.model.User
import com.texnopos.uz.instagramtexnopos.data.Resource

class EditViewModel(private val profileHelper: ProfileHelper) : ViewModel() {

    private var mutableUser: MutableLiveData<Resource<User>> = MutableLiveData()
    val user: LiveData<Resource<User>>
        get() = mutableUser


    fun getCurrentUser() {
        mutableUser.value = Resource.loading()
        profileHelper.getProfileData(
            {
                mutableUser.value = Resource.success(it)
            },
            {
                mutableUser.value = Resource.error(it)
            }
        )
    }

    private var mutableProfilEdit: MutableLiveData<Resource<String>> = MutableLiveData()
    val profilEdit: LiveData<Resource<String>>
        get() = mutableProfilEdit

    fun editProfile(user: User) {
        mutableProfilEdit.value = Resource.loading()
        profileHelper.editProfile(user,
            {
                mutableProfilEdit.value = Resource.success("succes")
            },
            {
                mutableProfilEdit.value = Resource.error(it)
            })
    }
}