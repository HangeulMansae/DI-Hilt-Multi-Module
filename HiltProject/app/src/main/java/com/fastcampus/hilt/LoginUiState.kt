package com.fastcampus.hilt

/**
 * @author soohwan.ok
 */
data class LoginUiState(
    val id:String,
    val pw:String,
    val userState: UserState = UserState.NONE
)