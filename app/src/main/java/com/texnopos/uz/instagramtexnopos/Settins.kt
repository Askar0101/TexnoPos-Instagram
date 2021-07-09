package com.texnopos.uz.instagramtexnopos

import android.content.Context

class Settins(private val context:Context) {

    companion object {
        const val KEY_SIGNED_IN="keySignedIn"
    }
    private val prefr=context.getSharedPreferences("${context.packageName}.prefr",Context.MODE_PRIVATE)

    var signedIn:Boolean

    set(value) = prefr.edit().putBoolean(KEY_SIGNED_IN,value).apply()

    get() = prefr.getBoolean(KEY_SIGNED_IN,false)


}