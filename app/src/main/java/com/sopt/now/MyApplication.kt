package com.sopt.now

import android.app.Application
import com.sopt.now.data.PreferenceUtil

class MyApplication : Application() {
    override fun onCreate() {
        userdata = PreferenceUtil(applicationContext)
        super.onCreate()
    }

    companion object {
        lateinit var userdata: PreferenceUtil
    }
}