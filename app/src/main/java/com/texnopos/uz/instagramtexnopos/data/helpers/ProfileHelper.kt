    package com.texnopos.uz.instagramtexnopos.data.helpers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.texnopos.uz.instagramtexnopos.data.N
import com.texnopos.uz.instagramtexnopos.data.model.User

class ProfileHelper(private val auth: FirebaseAuth, private val db: FirebaseFirestore) {
    fun getProfileData(onSuccess:(user:User)->Unit,onFailure:(msg:String?)->Unit){
        db.collection(N.USERS).document(auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val res=it.toObject(User::class.java)
                res?.let {result->
                    onSuccess.invoke(result)
                }?:onFailure.invoke("User data is empty")
            }.addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }
    fun editProfile(user: User,onSuccess: () -> Unit,onFailure: (msg: String?) -> Unit){
        db.collection(N.USERS).document(user.uid).set(user)
            .addOnSuccessListener {
                onSuccess.invoke()
            }.addOnFailureListener {
                onFailure.invoke(it.localizedMessage)
            }
    }
}