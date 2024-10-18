package com.example.shareworkspace.presentation.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shareworkspace.core.view_state.ViewState
import com.example.shareworkspace.data.data_source.remote.network.NetworkStatus
import com.example.shareworkspace.data.model.WorkspaceResponse
import com.example.shareworkspace.domain.usescase.GetNearByWorkspaceUseCase
import com.example.shareworkspace.domain.usescase.GetWorkspaceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel (private val useCase: GetWorkspaceUseCase, private val nearByWorkspaceUseCase: GetNearByWorkspaceUseCase, ) : ViewModel() {
    init {
        getWorkspace()
        getNearByWorkspace()
    }

    private val _workspace = MutableStateFlow<ViewState<List<WorkspaceResponse>>>(ViewState.Empty())
    val workspace: StateFlow<ViewState<List<WorkspaceResponse>>> = _workspace

    private fun getWorkspace() {
        viewModelScope.launch {
            useCase.execute().collect {
                val state = when (it.status) {
                    NetworkStatus.Success -> ViewState.Loaded(it.data!!)
                    NetworkStatus.Failure -> ViewState.Error(it.error!!)
                    else -> ViewState.Empty()
                }

                _workspace.value = state
            }
        }
    }

    private val _nearByWorkspace = MutableStateFlow<ViewState<List<WorkspaceResponse>>>(ViewState.Empty())
    val nearByWorkspace: StateFlow<ViewState<List<WorkspaceResponse>>> = _nearByWorkspace

    private fun getNearByWorkspace() {
        viewModelScope.launch {
            nearByWorkspaceUseCase.execute().collect {
                val state = when (it.status) {
                    NetworkStatus.Success -> ViewState.Loaded(it.data!!)
                    NetworkStatus.Failure -> ViewState.Error(it.error!!)
                    else -> ViewState.Empty()
                }

                _nearByWorkspace.value = state
            }
        }
    }


    // Amr

//    private val _workspace = MutableStateFlow<ViewState<List<WorkspaceResponse>>>(ViewState.Empty())
//    val workspace: StateFlow<ViewState<List<WorkspaceResponse>>> = _workspace
//
//    private fun getWorkspace() {
//        viewModelScope.launch {
//            useCase.execute().collect {
//                val state = when (it.status) {
//                    NetworkStatus.Success -> ViewState.Loaded(it.data!!)
//                    NetworkStatus.Failure -> ViewState.Error(it.error!!)
//                    else -> ViewState.Empty()
//                }
//
//                _workspace.value = state
//            }
//        }
//    }
}