package com.example.hsdemosoft

import android.app.Application
import com.example.hsdemosoft.di.AppComponents
import com.example.hsdemosoft.di.DaggerAppComponents

class MainApplication: Application() {

    companion object {
        lateinit var appComponents: AppComponents
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger(){
        appComponents = DaggerAppComponents
            .builder()
            .applicationContext(this)
            .build()
    }

}