package com.fastcampus.practice.ui

import java.util.UUID

class MyName {

    private val uuid: UUID = UUID.randomUUID()

    override fun toString(): String {
        return "${uuid}"
    }
}