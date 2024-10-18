package com.example.shareworkspace.core.view_state

import com.example.shareworkspace.data.data_source.remote.network.BaseException

open class ViewState<T>(val data:T? = null) {
    class Error<T>(val error: BaseException): ViewState<T>()
    class Empty<T>: ViewState<T>()
    class Loaded<T>(data:T): ViewState<T>(data)
}