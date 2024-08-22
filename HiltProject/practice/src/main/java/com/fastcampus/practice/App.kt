package com.fastcampus.practice

import android.app.Application
import android.util.Log
import com.fastcampus.practice.ui.MyName
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

private const val TAG = "App 싸피"
@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var myName: MyName

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate: My name is $myName")
    }
}