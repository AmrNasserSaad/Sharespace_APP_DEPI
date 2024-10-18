package com.example.shareworkspace.domain.repository

import com.example.shareworkspace.data.data_source.remote.network.NetworkResource
import com.example.shareworkspace.data.model.WorkspaceResponse
import kotlinx.coroutines.flow.Flow

interface IWorkspaceRepository {
    suspend fun getRemoteWorkspaces(): Flow<NetworkResource<List<WorkspaceResponse>>>
    suspend fun getNearByWorkspaces(): Flow<NetworkResource<List<WorkspaceResponse>>>
//    suspend fun getLocalWorkspaces(): Flow<WorkspacesEntity>

}