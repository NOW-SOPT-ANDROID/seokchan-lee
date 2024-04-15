package com.sopt.now.compose

import android.app.Application
import com.sopt.now.compose.data.Preferences

class MyApplication : Application() {

    override fun onCreate() {
        userdata = Preferences(applicationContext)
        super.onCreate()
    }

    companion object {
        lateinit var userdata: Preferences
    }
}