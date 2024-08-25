package com.fastcampus.practice

import javax.inject.Qualifier

// int 2개를 인자로 받는 복합 UserQualifier 생성
@Qualifier
annotation class UserQualifier(val age: Int, val height: Int) {
}