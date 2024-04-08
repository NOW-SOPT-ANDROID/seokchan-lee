package com.sopt.now

import android.app.Application
import com.sopt.now.data.preferenceUtil

class MyApplication : Application() {

    override fun onCreate() {
        userdata = preferenceUtil(applicationContext)
        super.onCreate()
    }

    companion object {
        lateinit var userdata: preferenceUtil
    }
}