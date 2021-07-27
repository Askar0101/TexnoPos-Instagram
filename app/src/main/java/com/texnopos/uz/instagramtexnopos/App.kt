package com.texnopos.uz.instagramtexnopos

import android.app.Application
import com.texnopos.uz.instagramtexnopos.di.dataModule
import com.texnopos.uz.instagramtexnopos.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val modules = listOf(dataModule, viewModelModule)
        startKoin {
            androidLogger()

            androidContext(this@App)

            androidFileProperties()

            koin.loadModules(modules)

        }
    }
}