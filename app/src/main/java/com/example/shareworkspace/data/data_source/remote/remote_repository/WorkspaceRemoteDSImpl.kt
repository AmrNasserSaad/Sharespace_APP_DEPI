package com.example.shareworkspace.data.data_source.remote.remote_repository

import com.example.shareworkspace.data.data_source.remote.interfaces.IWorkspaceRemoteDS
import com.example.shareworkspace.data.data_source.remote.network.ApiProvider
import com.example.shareworkspace.data.data_source.remote.network.ApiServices
import com.example.shareworkspace.data.data_source.remote.network.NetworkResource
import com.example.shareworkspace.data.model.WorkspaceResponse
import com.example.shareworkspace.utils.json
import kotlinx.coroutines.flow.Flow

class WorkspaceRemoteDSImpl(private val apiServices: ApiServices) : IWorkspaceRemoteDS,
    ApiProvider() {

    override suspend fun getWorkspaces(): Flow<NetworkResource<List<WorkspaceResponse>>> =
        apiRequest { apiServices.getWorkspaces() }

    override suspend fun getNearByWorkspaces(
        lat: Double,
        lon: Double
    ): Flow<NetworkResource<List<WorkspaceResponse>>> =
        apiRequest { apiServices.getNearByWorkspaces("location".json()!!, "$lat,$lon") }

}