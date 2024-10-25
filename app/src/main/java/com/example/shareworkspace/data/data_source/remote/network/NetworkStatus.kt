package com.example.shareworkspace.data.data_source.remote.network

sealed class NetworkStatus {
    data object Success : NetworkStatus()
    data object Failure : NetworkStatus()
    data object Loading : NetworkStatus()
    data object Idle : NetworkStatus()
}