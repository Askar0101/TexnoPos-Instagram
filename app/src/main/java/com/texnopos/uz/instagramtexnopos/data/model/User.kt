package com.texnopos.uz.instagramtexnopos.data.model

data class User(
    val uid:String="",
    val email:String="",
    var name:String="",
    var biography:String="",
    var image:String="",
    var postCount:Int=0,
    var followesrCount:Int=0,
    var followingsCount:Int=0
)
