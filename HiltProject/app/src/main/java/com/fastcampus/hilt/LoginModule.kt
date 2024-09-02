package com.fastcampus.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


// Activity Component로 선언하면 ViewModel에서 접근을 할 수가 없기 때문에 ViewModelComponent와 ActivityComponent의 상위인 ActivityRetainedComponent로 InstallIn을 해야 함
//@InstallIn(ActivityComponent::class)
@Module
@InstallIn(ActivityRetainedComponent::class)
class LoginModule {
    @Provides
    @ActivityRetainedScoped
    // UserLocalDataSource와 UserRemoteDataSource는 이미 APPModule에서 의존성 추가를 해줬으므로 그냥 바로 인자로 넣어서 쓰면 됨
    fun provideUserDataRepository(localDataSource: UserLocalDataSource, userRemoteDataSource: UserRemoteDataSource): UserDataRepository {
        return UserDataRepository(
            localDataSource = localDataSource,
            remoteDataSource = userRemoteDataSource
        )
    }

    // Hilt를 사용하므로 UserDataRepository를 의존성으로 추가하는 Code를 만들어야 함 => Provides로 생성
//    private val userDataRepository = UserDataRepository(
//        localDataSource = provideUserLocalDataSource(),
//        remoteDataSource = appContainer.createUserRemoteDataSource()
//    )

    // Hilt에서는 ViewModel을 위한 Annotation을 제공하고 있고 여기서 기타 보일러 플레이트 코드들을 처리해주므로 없어도 됨
//    fun createLoginViewModelFactory(): AbstractSavedStateViewModelFactory {
//        return object : AbstractSavedStateViewModelFactory() {
//            override fun <T : ViewModel> create(
//                key: String, modelClass: Class<T>, handle: SavedStateHandle
//            ): T {
//                return LoginViewModel(userDataRepository) as T
//            }
//        }
//    }
}