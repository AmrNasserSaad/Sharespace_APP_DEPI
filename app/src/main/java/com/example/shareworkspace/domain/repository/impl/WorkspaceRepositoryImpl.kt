package com.example.shareworkspace.domain.repository.impl

import com.example.shareworkspace.data.data_source.local.prefs.PreferenceHelper
import com.example.shareworkspace.data.data_source.remote.interfaces.IWorkspaceRemoteDS
import com.example.shareworkspace.data.data_source.remote.network.NetworkResource
import com.example.shareworkspace.data.model.WorkspaceResponse
import com.example.shareworkspace.domain.repository.IWorkspaceRepository
import kotlinx.coroutines.flow.Flow

class WorkspaceRepositoryImpl(
    private val remoteDS: IWorkspaceRemoteDS,
    private val prefs: PreferenceHelper
) : IWorkspaceRepository {

    override suspend fun getRemoteWorkspaces(): Flow<NetworkResource<List<WorkspaceResponse>>> =
        remoteDS.getWorkspaces()

    override suspend fun getNearByWorkspaces(): Flow<NetworkResource<List<WorkspaceResponse>>> =
        remoteDS.getNearByWorkspaces(prefs.lat, prefs.lon)
//    override suspend fun getLocalWorkspaces(): Flow<WorkspaceEntity> {
//       //room db
//    }
}