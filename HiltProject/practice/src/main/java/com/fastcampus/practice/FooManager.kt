package com.fastcampus.practice

import android.content.Context
import android.util.Log
import dagger.hilt.EntryPoints

private const val TAG = "FooManager_싸피"
class FooManager {

    fun doSomething(context: Context){
        // 만약 생성자 Binding을 못쓰는 상황이라면
        val fooEntryPoint = EntryPoints.get(context, FooEntryPoint::class.java)
        val foo  = fooEntryPoint.getFoo()
        Log.e(TAG, "doSomething: foo = ${foo}", )
    }
}