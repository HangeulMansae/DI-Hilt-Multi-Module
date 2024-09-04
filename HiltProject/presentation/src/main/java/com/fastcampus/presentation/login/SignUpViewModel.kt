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
) : ViewModel(), ContainerHost<SignUpState, SignUpSideEffect> {
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

    fun onSignUpClick() = intent{
        val id = state.id
        val username = state.username
        val password = state.password
        val repeatPassword = state.repeatPassword

        if(password == repeatPassword){
            val isSuccessful = signUpUseCase(id, username, password).getOrThrow()
            if(isSuccessful){
                postSideEffect(SignUpSideEffect.Toast("회원가입에 성공했습니다"))
                postSideEffect(SignUpSideEffect.NavigateToLoginScreen)
            }
            Log.e(TAG, "onLoginClick: ", )
        }else{
            postSideEffect(SignUpSideEffect.Toast("패스워드가 일치하지 않습니다."))
            return@intent
        }
    }

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

@Immutable
data class SignUpState(
    val id: String = "",
    val username: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)

// 상태 즉 State와 관련 없는 부분
sealed interface SignUpSideEffect{
    class Toast(val message:String): SignUpSideEffect

    object NavigateToLoginScreen : SignUpSideEffect
}