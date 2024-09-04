package com.fastcampus.presentation.login

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import com.fastcampus.domain.usecase.login.LoginUseCase
import com.fastcampus.domain.usecase.login.SetTokenUseCase
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
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setTokenUseCase: SetTokenUseCase
) : ViewModel(), ContainerHost<LoginState, LoginSideEffect> {
    override val container: Container<LoginState, LoginSideEffect> = container(
        initialState = LoginState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
                intent {
                    postSideEffect(LoginSideEffect.Toast(message = throwable.message.orEmpty()))
                }
            }
        }
    )

    fun onLoginClick() = intent{
        val id = state.id
        val password = state.password
        val token = loginUseCase(id, password).getOrThrow()
        setTokenUseCase(token)
        postSideEffect(LoginSideEffect.NavigateToMainActivity)
//        postSideEffect(LoginSideEffect.Toast(message = "token = $token"))
        Log.e(TAG, "onLoginClick: ", )
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
}

@Immutable
data class LoginState(
    val id: String = "",
    val password: String = ""
)

// 상태 즉 State와 관련 없는 부분
sealed interface LoginSideEffect{
    class Toast(val message:String): LoginSideEffect
    object NavigateToMainActivity: LoginSideEffect
}