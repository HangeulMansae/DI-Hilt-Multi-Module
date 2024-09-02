package kr.co.fastcampus.sns

import android.app.Application
import com.fastcampus.hilt.AppContainer

/**
 * @author soohwan.ok
 */
class App : Application() {
    val appContainer: AppContainer = AppContainer(context = this)
}