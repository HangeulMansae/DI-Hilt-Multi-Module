package com.fastcampus.practice

import javax.inject.Inject

class Car @Inject constructor(
    val engine: Engine
) {
}