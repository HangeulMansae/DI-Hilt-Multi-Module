package com.fastcampus.hilt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author soohwan.ok
 */
@HiltViewModel
// 생성자 Binding으로 LoginViewModel이 ViewModelComponent의 의존성으로 추가가 되는 것과 동시에, 생성자의 인자로 받는 UserDataRepository 인자의 의존성을 가져와서 세팅해줌
// => 이미 LoginModule에서 의존성을 삽입해 놓음
class LoginViewModel @Inject constructor(
    private val repository: UserDataRepository,
):ViewModel(){

    private val _uiState = MutableStateFlow(
        LoginUiState(id = "", pw = "")
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            if(repository.isLoggedIn()){
                val isLoggedInBefore = repository.isLoggedIn()
                if(isLoggedInBefore){
                    _uiState.update { it.copy(userState = UserState.LOGGED_IN) }
                }
            }
        }
    }

    fun login(){
        viewModelScope.launch(Dispatchers.IO) {
            val isLoggedIn = repository.login(
                _uiState.value.id,
                _uiState.value.pw
            )
            val token = repository.getCurrentToken()
            _uiState.update {
                it.copy(userState = if(isLoggedIn) UserState.LOGGED_IN else UserState.FAILED)
            }
        }
    }

    fun onIdChange(value: String) {
        _uiState.update { it.copy(id = value) }

    }

    fun onPwChange(value: String) {
        _uiState.update { it.copy(pw = value) }
    }

}