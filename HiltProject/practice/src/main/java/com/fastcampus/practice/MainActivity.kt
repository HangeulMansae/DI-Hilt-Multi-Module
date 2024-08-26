package com.fastcampus.practice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fastcampus.practice.ui.theme.HiltTheme
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

private const val TAG = "MainActivity_싸피"
// Component에 대해서 진입점을 설정해주지 않으면 super.onCreate() 이후에 의존성을 호출해도 not initialize됨
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // 필드 주입
//    @Inject
//    lateinit var foo: Foo
//    @Inject
//    lateinit var bar: Bar


    @Inject
    lateinit var providerFoo1: Provider<Foo>
    @Inject
    lateinit var providerFoo2: Provider<Foo>
    //lazy 방식
//    lateinit var lazyFoo: Lazy<Foo>
    // 만약 Binding을 만든 적 없는 Qualifier 값으로 annotation을 쓰면 에러 남
    // ex : UserQualifier(10, 180)
    @UserQualifier(50, 180)
    @Inject
    lateinit var charles: User

    @UserQualifier(10, 120)
    @Inject
    lateinit var john: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        val foo1 = providerFoo1.get()
//        val foo2 = providerFoo1.get()
//        assert(foo1 !== foo2)

//          Foo가 Singleton일 경우에는 foo1과 foo2가 같음
//          Foo가 Singleton이 아니면 다름
//        val foo1 = providerFoo1.get()
//        val foo2 = providerFoo2.get()
//        assert(foo1 !== foo2)
//        assert(foo1 === foo2 )

//        assert(this::lazyFoo.isInitialized)
//        val foo: Foo = lazyFoo.get()
//        assert(foo != null)
//        assert(this::bar.isInitialized)
//        assert(foo.bar != null)
        Log.e(TAG, "onCreate: #${charles.name}", )
        Log.e(TAG, "onCreate: #${john.name}", )



//        assert(this::lazyFoo.isInitialized)
        setContent {
            HiltTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        "",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    // 메서드 주입
//    @Inject
//    fun injectFoo(
//        // CustomQualifier로 만든 것으로 의존성을 주입하기 위해 의존성 앞에 annotation을 명시해줌
//        //@CustomQualifier foo: Foo
//        // Named로 중복 Binding 처리
//        @Named("Foo") foo: Foo
//    ){
//        Log.e(TAG, "injectFoo: ${foo.id}", )
//        this.foo = foo
//    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HiltTheme {
        Greeting("Android")
    }
}