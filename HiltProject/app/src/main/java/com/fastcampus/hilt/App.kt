package com.fastcampus.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @author soohwan.ok
 */

@HiltAndroidApp
class App : Application() {
    // @HiltAndroidApp을 통해 SingletonComponent가 생성이 될 것이므로 더이상 appContainer는 필요가 없음
//    val appContainer: AppContainer = AppContainer(context = this)
}