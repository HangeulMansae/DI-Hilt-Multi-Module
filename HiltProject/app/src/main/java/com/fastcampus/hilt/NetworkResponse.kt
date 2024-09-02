package com.fastcampus.hilt

import kotlinx.serialization.Serializable

/**
 * @author soohwan.ok
 */
@Serializable
data class NetworkResponse<T>(
    val result:String,
    val data:T,
    val errorMessage:String
)