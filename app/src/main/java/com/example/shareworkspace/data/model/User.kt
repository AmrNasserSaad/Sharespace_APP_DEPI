package com.example.shareworkspace.data.model

import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("date") val date: String = "",
    @SerializedName("duration") val duration: String = "",
    @SerializedName("number_of_clients") val numberOfClients: String = "",
    @SerializedName("rate_workspace") val rateWorkspace: Int = 0,
    @SerializedName("review_text") val reviewText: String = "",
    @SerializedName("time") val time: String = "",
    @SerializedName("workspace_id") val workspaceId: Int = 0,
    @SerializedName("workspace_name") val workspaceName: String = ""
)

data class User(
    @SerializedName("latitude") var latitude: Double = 0.0,
    @SerializedName("longitude") var longitude: Double = 0.0,
    @SerializedName("uid") val uid: String = "",
    @SerializedName("firstName") val firstName: String? = null,
    @SerializedName("lastName") val lastName: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("history") val history: List<History> = listOf(),
    @SerializedName("email") val email: String? = null,
    @SerializedName("phone") val phone: String? = null,

)