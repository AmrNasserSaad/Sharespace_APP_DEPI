package com.example.shareworkspace.domain.usescase

import com.example.shareworkspace.data.data_source.remote.network.NetworkResource
import com.example.shareworkspace.data.model.WorkspaceResponse
import com.example.shareworkspace.domain.repository.IWorkspaceRepository
import kotlinx.coroutines.flow.Flow

class GetWorkspaceUseCase(private val workspaceRepository: IWorkspaceRepository) {
    suspend fun execute(): Flow<NetworkResource<List<WorkspaceResponse>>> = workspaceRepository.getRemoteWorkspaces()
}