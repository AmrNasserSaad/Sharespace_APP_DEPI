package com.example.shareworkspace.data.data_source.remote.interfaces

import com.example.shareworkspace.data.data_source.remote.network.NetworkResource
import com.example.shareworkspace.data.model.WorkspaceResponse
import kotlinx.coroutines.flow.Flow


interface IWorkspaceRemoteDS {

    suspend fun getWorkspaces(): Flow<NetworkResource<List<WorkspaceResponse>>>
    suspend fun getNearByWorkspaces(lat: Double, lon: Double): Flow<NetworkResource<List<WorkspaceResponse>>>
}