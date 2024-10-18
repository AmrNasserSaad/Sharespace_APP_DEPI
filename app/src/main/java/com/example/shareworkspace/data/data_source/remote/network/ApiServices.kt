package com.example.shareworkspace.data.data_source.remote.network


import com.example.shareworkspace.data.model.WorkspaceResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    companion object {
        fun createApiService(retrofit: Retrofit): ApiServices =
            retrofit.create(ApiServices::class.java)
    }

    @GET("workspace.json")
    suspend fun getWorkspaces(): Response<List<WorkspaceResponse>>

    @GET("workspace.json")
    suspend fun getNearByWorkspaces(
        @Query("orderBy") by: String,
        @Query("equalTo") location: String
    ): Response<List<WorkspaceResponse>>


}