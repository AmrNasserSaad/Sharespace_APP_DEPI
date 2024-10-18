package com.example.shareworkspace.domain.usescase

import com.example.shareworkspace.domain.repository.IWorkspaceRepository

class GetNearByWorkspaceUseCase(private val iWorkspaceRepository: IWorkspaceRepository) {
    suspend fun execute() = iWorkspaceRepository.getNearByWorkspaces()
}