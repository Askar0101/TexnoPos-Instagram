package com.texnopos.uz.instagramtexnopos.di
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.texnopos.uz.instagramtexnopos.data.Settins
import com.texnopos.uz.instagramtexnopos.data.helpers.AuthHelper
import com.texnopos.uz.instagramtexnopos.data.helpers.PostHelper
import com.texnopos.uz.instagramtexnopos.data.helpers.ProfileHelper
import com.texnopos.uz.instagramtexnopos.ui.account.AccountViewModel
import com.texnopos.uz.instagramtexnopos.ui.account.edit.EditViewModel
import com.texnopos.uz.instagramtexnopos.ui.add.AddFragmentViewModel
import com.texnopos.uz.instagramtexnopos.ui.auth.signin.SignInViewModel
import com.texnopos.uz.instagramtexnopos.ui.auth.signup.SignUpViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirebaseStorage.getInstance() }
    single { AuthHelper(get(), get()) }
    single { ProfileHelper(get(), get()) }
    single { PostHelper(get(), get(), get()) }
    single { Settins(androidContext()) }
}
val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }
    viewModel { AccountViewModel(get()) }
    viewModel { EditViewModel(get()) }
    viewModel { AddFragmentViewModel(get()) }
}