package com.fastcampus.presentation.login

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.fastcampus.domain.usecase.login.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

private const val TAG = "LoginViewModel_싸피"
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel(),
    // MVI로 상태를 관리하기 위한 State와 SideEffect를 관리하기 위한 Interface를 가지는 ContainerHost를 생성
    ContainerHost<SignUpState, SignUpSideEffect> {
        // 초기 상태와 exceptionHandler 등에 대해서 설정 및 관리하는 Container override
    override val container: Container<SignUpState, SignUpSideEffect> = container(
        initialState = SignUpState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
                intent {
                    postSideEffect(SignUpSideEffect.Toast(message = throwable.message.orEmpty()))
                }
            }
        }
    )
    // SideEffect나 상태 변화(Reduce)를 하기 위해 Orbit의 intent 블럭을 열어서 비동기적으로 돌아가는 내부 로직을 작성
    fun onSignUpClick() = intent{
        val id = state.id
        val username = state.username
        val password = state.password
        val repeatPassword = state.repeatPassword

        if(password == repeatPassword){
            val isSuccessful = signUpUseCase(id, username, password).getOrThrow()
            if(isSuccessful){
                // 현재 UI에는 옇향이 없는 SideEffect 생성
                postSideEffect(SignUpSideEffect.Toast("회원가입에 성공했습니다"))
                postSideEffect(SignUpSideEffect.NavigateToLoginScreen)
            }
            Log.e(TAG, "onLoginClick: ", )
        }else{
            postSideEffect(SignUpSideEffect.Toast("패스워드가 일치하지 않습니다."))
            return@intent
        }
    }

    // reduce로 상태 변화를 시켜야 하지만, 비동기적이 아니라 동기적으로 돌리고 싶을 때는 blockingIntent 코드 내에 작성
    fun onIdChange(id: String) = blockingIntent{
        reduce{
            state.copy(id = id)
        }
    }

    fun onPasswordChange(password: String) = blockingIntent{
        reduce {
            state.copy(password = password)
        }
    }

    fun onRepeatPasswordChange(password: String) = blockingIntent{
        reduce {
            state.copy(repeatPassword = password)
        }
    }
    fun onUsernamechange(username: String) = blockingIntent{
        reduce{
            state.copy(username = username)
        }
    }
}

// MVI에서 ContainerHost가 관리하게 할 State를 정의한 DataClass
@Immutable
data class SignUpState(
    val id: String = "",
    val username: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)

// 싱태와 관련 없는 SideEffect들을 나열한 sealed Interface
// 상태 즉 State와 관련 없는 부분
sealed interface SignUpSideEffect{
    class Toast(val message:String): SignUpSideEffect

    object NavigateToLoginScreen : SignUpSideEffect
}