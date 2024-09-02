package com.fastcampus.practice

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import com.fastcampus.DialogComponentManager
import dagger.hilt.EntryPoints
import java.time.LocalDateTime
import javax.inject.Inject

class MyDialog @Inject constructor(
    private val context: Activity,
    private val dialogComponentManager: DialogComponentManager
): Dialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val entryPoint: DialogEntryPoint = EntryPoints.get(dialogComponentManager, DialogEntryPoint::class.java)
        val user : User = entryPoint.getUser()
        val randomNumber1: Int = entryPoint.getRandomNumber()
        val randomNumber2: Int = entryPoint.getRandomNumber()
        val randomNumber3: Int = entryPoint.getRandomNumber()
        val localDateTime: LocalDateTime = entryPoint.getLocalDateTime()
        setContentView(TextView(context).apply {
            text = "${user.name}\n ${randomNumber1}\n" +
                    " ${randomNumber2}\n" +
                    " ${randomNumber3}\n" +
                    "${localDateTime}"
        })
    }
}