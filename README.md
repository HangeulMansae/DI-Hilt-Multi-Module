# DI-Hilt-Multi-Module

## 의존성이란
어떤 대상이 참조하는 객체 또는 함수

ex:)
자동차를 예시로 들 때 자동차는 엔진이 없이는 구성될 수 없음 => 코드적으로는 Engine을 import 하지 않거나 초기화 하지 않고서는 차가 compile 되지 않음

이를 자동차는 엔진에 의존한다라고 함 또한 엔진을 의존성이라고 함

+ ### 의존성 주입
    대상 객체에 의존성을 제공하는 기술

    ex:) 아까 예시를 기준으로 Car는 엔진을 생성하는 책임을 가짐

    만약 엔진을 생성자의 매개변수로 받는다면, 자동차는 엔진 생성의 책임이 없어짐

    이러한 설계 패턴을 IoC (Inversion of Control, 제어 역전)이라고 함

    + #### IoC
        객체 생성의 책임을 내부에서 외부로 뒤집으면서 엔진에 대한 제어를 역전시키는 것
    
    + #### 장점
        + ##### 외부에서 생성된 것을 받기 때문에, Car 자체에 대해서 수정 없이도 여러 엔진을 받아서 처리할 수 있음
            => 수정 없이 다양하게 사용할 수 있으므로 Car 클래스의 **재사용성이 좋아짐**

            => 엔진을 초기화 하지 않고 **외부에서 주입 받고 있기 때문에 클래스 간 결합도가 낮춰짐 ( 디커플링 )**

            => 필요한 것을 외부에서 주입받으므로 Car가 가지고 있던 길었던 소스코드, 책임이 줄어듦

        + ##### 테스트가 쉬워짐
            외부에서의 객체 주입을 통해서 테스트 해볼 수 있음
    
    + #### 장점 요약
        + ##### 결합도가 낮아짐
        + ##### 재사용성이 가능해짐
        + ##### 보일러플레이트 감소
        + ##### 테스트가 쉬움
        + ##### 의존성 관리가 용이함 (자원 공유)

    + #### 보일러플레이트란
        여러곳에서 재사용되며, 반복적으로 비슷한 형태를 띄는 코드를 의미

## Injector
의존성을 Client에게 제공하는 역할

만약 기존에 아래와 같은 코드가 있었을 때
```kotlin
fun main(Args:Array<String>){
    val engine = Injector().getEngine()
    val car = Car(engine)
}
```

직접 main에서 초기화 하지 않고, 아래와 같이 엔진을 생성하는 Injector를 만들어서 Injector를 통하여 생성된 Engine을 받아 건네주는 것이 이상적
```kotlin
// Engine을 만들어서 건네주는 역할을 위한 Injector
class Injector{
    fun getEngine(){
        return Engine()
    }
}

fun main(args:Array<String>){
    val engine = Injector().getEngine()
    val car = Car(engine)
}
```

엔진을 호출할 때마다 새로운 Engine을 초기화 해서 건네주지만, 만약 동일한 Engine을 공유하고 싶다면 Injector 내부에서 동일한 Instance를 참조할 수 있도록 코드를 작성해야 함

+ ### Injector의 또다른 명칭
    + Container
    + Assembler
    + Provider
    + Factory

## Flow란
Coroutine의 Flow는 Data Stream, Coroutine 상에서 반응형 프로그래밍을 지원하기 위한 구성요소

Coroutine에서 Data Stream을 지원하기 위해서는 Flow를 사용해야 함

기존의 명령형 프로그래밍에서는 Data의 소비자는 Data를 요청한 후 받은 결과값을 일회성으로 수신함

이러한 점은 Data가 필요할 떄마다 결과값을 매번 요청해야한다는 점에서 매우 비효율적

반응형에서는 Data의 Publisher와 Subscriber가 있고, Data의 발행자는 새로운 Data가 들어오면 소비자에게 지속적으로 발행하는 역할을 함

이것을 **Data Stream**이라고 함


+ ### 명령형 프로그래밍이란
    컴퓨터에게 무엇을 하라고 명령하는 것

    일반적으로 우리가 알고 있는 방식
    
    ex:)
    ```kotlin
    // 출력을 하라고 명령함
    println("count : 1")
    println("count : 2")
    println("count : 3")
    ```

+ ### 반응형 프로그래밍이란
    데이터가 변경될 때 Event를 발생시켜서 Data를 계속해서 전달하도록 하는 프로그래밍 방식

    Publisher와 Subscriber가 존재
    
    + #### Publisher
        발행자로, 자신을 구독하고 있는 구독자에게 값을 발행함
    
    + #### Subscriber
        발행자에게서 값이 오기를 구독하고서, 값이 왔을 때 Logic을 실행함

    + #### 예시
        ```kotlin
        fun main(){

            // publisher
            val publisher: PublisherSubject<String> = PublishSubject.create()

            // subscriber, 값이 왔을 때 출력하는 로직으로 설정
            publisher.subscribe{ it -> 
                println(it)
            }

            // publisher에서 값을 발행함 => subscriber는 값이 전달된 것을 파악하고, 이에 따른 자신의 로직을 실행함
            publisher.onNext("count : 1")
            publisher.onNext("count : 2")
            publisher.onNext("count : 3")

            // 결과
            count : 1
            count : 2
            count : 3
        }
        ```

        예전에는 setOnClickListener 등 사용자의 동작 Event에 따라 행동해야 하는 로직을 직접 명시해줘야 했지만,
        
        반응형으로 한다면 동작 Event에 따라 Publisher로 Data를 발행시키기만 하면, 해당 발행자에 대한 구독자의 동작만 신경쓰면 되기 때문에 관리할 것이 줄어듦

        => 구독자가 Publisher의 값에 따라 어떤 로직을 수행할지를 설정해놓으면 됨

        ```kotlin
        button1.setOnClickListener{
            publisher.onNext(BUTTON_CLICK_EVENT)
        }
        button2.setOnClickListener{
            publisher.onNext(BUTTON_CLICK_EVENT)
        }
        button3.setOnClickListener{
            publisher.onNext(BUTTON_CLICK_EVENT)
        }
        
        publisher.subscribe{
            if(it == BUTTON_CLICK_EVENT)
                doSomething()
        }
        ```
    
+ ### Data Stream 구조
    + ### Producer (생산자, Data 생성)
        Data를 발행하는 역할

        Flow에서 Producer는 **flow{} 블록 내부에서의 emit()을 통해 Data를 생성**

        ex:)
        ```kotlin
        class DustRemoteDataSource(
            private val dustApi: DustApi
        ){
            fun getDustInfoFlow() : Flow<List<DustInfo>> = flow{ // 1. Flow 블록 선언
                while(true){
                    val dustInfos = dustApi.fetchLastedDustInfo() // 2. 데이터 받아오기
                    emit(dustInfos) // 3. Producer가 Data 발행
                    delay(INTERVAL_REFRESH) // 4. 60초마다 반복
                }
            }
        }

        companion object {
            private const val INTERVAL_REFRESH: Long = 60000
        }
        ```
    + ### Intermediary (중간 연산자, Data 변환)
        생성된 Data를 수정하는 역할

        생성자가 A라는 객체로 이루어진 Data를 발행했는데, B라는 객체 Data가 필요할 경우, Flow에서 중간 연산자를 이용해 A 객첼르 B 객체로 변환할 수 있음

        **대표적으로 map(데이터 변형), filter(데이터 필터링), onEach(모든 데이터마다 연산 수행) 등의 중간 연산자가 있음**

        위 미세먼지 코드 예에서, 만약 우리 지역 데이터의 미세먼지 데이터만 필요하다면, map으로 데이터를 변형하면서, filter로 특정 지역의 Data만을 방출할 수 있음

        ```kotlin
        class DustRepository(
            private val dustRemoteDataSource: DustRemoteDataSource
        ){
            fun getDustsInfoOfViewItem(locale : Locale) = 
                dustRemoteDataSource.getDustInfoFlow().map{ it.filter{ this.locale == locale}}
        }
        ```

    + ### Consumer (소비자, Data 소비)
        **collect를 이용하여 전달된 Data를 소비할 수 있음**
        ```kotlin
        class DustViewModel(
            private val dustREpository: DustRepository
        ) : ViewModel(){
            fun collectDustInfoOf(locale: Locale) = 
                viewModelScope.launch{
                    dustRepository.getDustInfoOViewItemf(locale).collect { dustInfos ->
                        // 데이터가 들어왔을 때 실행할 로직
                    }
                }
        }
        ```
    
    + ### 요약
        DataSource에서 Data를 Publisher해서, Repository에서 Data를 수정하고, ViewModel에서 데이터를 소비


## Shared Preference와 Data Store
+ ### DataStore란
    Protocol Buffer를 사용하여 키-값 쌍 또는 유형이 지정된 객체를 저장할 수 있는 데이터 저장소

    Coroutine 및 Flow를 사용하여 비동기적이고 일관된 Transaction 방식으로 데이터를 저장하는 것이 특징

    기본적으로 Dispatcher.IO 밑에서 작동하며 Runtime Exception으로부터 안전

    Key-Value로 구성되어 있는 Preferences DataStore, 사용자가 정의한 데이터를 저장할 수 있는 Proto DataStroe가 존재

    Proto DataStore를 사용할 시 PRotocol Buffer라는 것을 이용해 Scheme를 정의해야 함

    => DataType을 보장해줄 뿐 아니라 SharedPreferences보다 빠르고 단순함

    DataStore는 Singleton 방식으로 하나의 객체로 관리해야 함

    + #### Protocol Buffer란?
        Data를 직렬화 하기 위한 메커니즘

    + #### 장점
        + Flow를 지원하며, Data에 변경이 발생해도 collect 하고 있다면 자동으로 Data를 전달받을 수 있음
        + 자동으로 Dispatcher.IO에서 작동하여 백그라운드에서 작업을 진행함
        + Flow의 catch 연산자를 통해 에러를 적절히 처리할 수 있음
        + 모든 작업이 하나의 Transaction 내에서 Atomic하게 발생하기 때문에 안전함
        + Proto Datastore 사용 시, type safe한 장점이 있어 RunTime Error 가능성 줄일 수 있음
        + 대용량의 데이터와 복잡한 데이터 구조를 보아 효율적으로 관리할 수 있음

    + #### 코드 예시
        ```kotlin
        // DataStore 생성
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "이름")
    
        // DataStore 읽기
        // INT값의 value를 가져오는 Key 생성
        val EXAMPLE_COUNTER = intPreferencesKey("example")
        val exampleCounterFlow: Flow<Int> = context.dataStore.data
            .map { preferences ->
                // type-safe 하지 않음
                preferences[EXAMPLE_COUNTER] ?: 0
            }

        // DataStore 쓰기
        suspend fun incrementCounter(){
            // edit method를 사용하여 DataStore에 접근하고 key를 통해 값을 가져와 업데이트 함
            context.dataStore.edit{ settings ->
                val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
                settings[EXAMPLE_COUNTER] = currentCounterVAlue + 1 // 마지막 라인에 변환 할 값 작성
            }
        }
        ```
    + #### Emit, Edit 차이
        + ##### Edit
            **DataStore의 값을 수정, 저장할 떄 사용**하는 것
            
        + ##### Emit
            **Flow에서 새로운 값 방출할 때 사용**하는 것


## Hilt
Android를 위한 표준적인 DI 솔루션을 제공

보일러플레이트 감소

Jetpack과 통함

테스트 도구 제공

마이그레이션 API 제공

+ ### 등장 배경
    기존 Android 언어는 JAVA였기 때문에 의존성 주입 또한 JAVA 방식을 따랐음
    
    (JSR 330, JAVA의 의존성 입을 위한 표준 방법을 정의한 내용)

    1. Guice
        + 자바를 위한 의존성 주입
        + Runtime에 의존성 주입
        + Reflection 기반

            RunTime에 Load된 다른 클래스 정보들을 가져올 수 있는 장점이 있지만, 비용이 큼 
            
            => Performance가 떨어지게 됨
    2. Dagger2
        + 자바를 위한 의존성 주입
        + 컴파일 타임 코드 생성
        + Square에서 최초 개발, 이후 구글에서 유지 보수
        + Reflection을 사용하지 않음
    3. Koin
        + Kotlin을 위한 의존성 주입
        + Reflection 사용
        + Serivce Locator Pattern
    4. Hilt
        + 2020년 6월 최초 출시
        + Android만을 위한 Solution
        + Dagger2 기반
        + 표준 컴포넌트 제공


+ ### Koin, Dagger2 비교

    |분류| Koin|Dagger1(Hilt)|
    |:---:|:---:|:---:|
    |**Runtime 안정성**|낮음|높음|
    |**Performance**|떨어짐|높음|
    |**의존성 관리 난이도**|어려움|쉬움|

    Koin은 의존성 체크를 Runtime에 실시 => Runtime에 Crash가 발생할 수도 있음

    Koin은 Reflection을 사용하기 때문에 Performance가 떨어짐

    Koin은 시간의 흐름에 따라 커졌을 때 의존성 그래프를 파악하기 어려움

+ ### Dagger2에 대해서
    + Annotation Processor로 자동 코드 생성

        Compile 타임에 오류를 Check 할 수 있고, Reflection을 사용할 필요 X
    + Running Curve가 높음

    + 높은 자유도

        => 동일한 문제를 해결할 때 개발자마다 서로 다른 방식으로 해결할 수가 있음

        => 혼란을 야기시킴

## Hilt 적용하기

+ ### @HiltAndroidApp

    Application class에 마킹해야 함





+ ### Binding (코드 X 개념 O)

    컴포넌트에 의존성을 추가하는 것




+ ### @AndroidEntryPoint
    Activity등 Android Component등에 마킹함

    마킹하면 ActvityRetainedComponent가 생성됨

+ ### @Inject
    두가지의 의미를 가짐

    변수 위에 선언할 때는 의존성 주입을 요청한다는 의미

    생성자 앞에 선언할 때는 해당 클래스의 의존성을 Component에 Binding 하겠다라는, 의존성을 추가하겠다라는 의미

+ ### 생성자를 통한 의존성 Binding 방법
    ```kotlin
    class App : Application(){
        
        // MyName 형식의 의존성을 주입해줄 것을 요청
        @Inject
        lateinit var myName: MyName

        override fun onCreate(){
            // super.onCreate()에서 의존성 주입이 이루어지므로, 그 이전에 의존성을 불러오려고 하면 error가 남
            super.onCreate()
            Log.e(TAG, "My name is $myName")
        }
    }

    // MyName이라는 의존성을 추가하겠다는 의미로 constructor 앞에 @Inject 추가
    class MyName @Inject constructor(){
        override fun toString(): String{
            return "예시"
        }
    }
    ```

+ ### Module을 통한 Binding 방법
    ```kotlin
    class App : Application(){
        
        // MyName 형식의 의존성을 주입해줄 것을 요청
        @Inject
        lateinit var myName: MyName

        override fun onCreate(){
            // super.onCreate()에서 의존성 주입이 이루어지므로, 그 이전에 의존성을 불러오려고 하면 error가 남
            // 꼭 이것뿐 아니라 Activity 등 Component에서 진입점(EntryPoint)를 설정하지 않아도 error가 남
            super.onCreate()
            Log.e(TAG, "My name is $myName")
        }
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule{

        @Provides
        funj provideMyName():MyName{
            return MyName()
        }
    }

    // MyName이라는 의존성을 추가하겠다는 의미로 constructor 앞에 @Inject 추가
    class MyName {
        override fun toString(): String{
            return "예시"
        }
    }
    ```

> **super.onCreate()에서 의존성 주입이 이루어지므로, 그 이전에 의존성을 불러오려고 하면 error가 남**

> **또한 Component에 대해서 진입점을 설정해주지 않으면, super.onCreate() 이후에 변수를 호출을 해도 not initialize error 발생**

+ ### Annotation
    + #### Hilt Annotation 종류
        + ##### @HiltAndroidApp
        + ##### @AndroidEntryPoint
        + ##### @Module
        + ##### @InstallIn
        + ##### @HiltViewModel
    
    + #### Annotation Processor
        컴파일 타임에 어노테이션을 스캔하고, 소스 코드를 검사 또는 생성함

        Round : 어노테이션을 처리하는 과정

        소스 내의 모든 어노테이션이 처리될 때까지 몇차례의 라운드를 거치면서 어노테이션 프로세서가 코드를 스캔하고 처리함

    + #### Hilt Annotation 처리 요약
        + ##### Hild Annotatino을 사용하여 부가 정보를 제공
        + ##### Compile Time에 의존성 그래프에 이상이 없는지 확인
        + ##### 생성된 소스코드를 기반으로 동작하므로 Reflection을 사용하지 않아도 됨

+ ### Byte 코드 변조
    + #### Byte코드란?
        자바 소스코드가 컴파일을 거쳐 나온 결과물

    + #### Transform API
        AGP(Android Gradle Plugin)에 포함된 API

        중간 빌드 산출물들을 처리
        
        바이트 코드 변환을 위한 Gradle Task 생성

        AGP는 변조된 내용들 사이의 의존성을 Handling

    + #### Byte 코드 변조 이유
        Hilt 사용 시 필수는 아님

        소스 코드를 해치지 않으면서 Hilt를 사용할 때 편리하게 의존성 주입을 할 수 있도록 도와줌

        Hilt를 사용할 때 의존성 주입을 위해서 Hilt_~를 반드시 참조 해야 함

        => 이를 Annotation을 통해서 compile 과정 중에 변환 시켜줌