package com.example.shareworkspace.data.data_source.remote.network

import com.google.gson.annotations.SerializedName

data class BaseException(
    @SerializedName("status_code") val code: Int,
    @SerializedName("status_message") val message: String
)
