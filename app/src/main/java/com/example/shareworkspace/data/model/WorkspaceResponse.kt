package com.example.shareworkspace.data.model

import com.google.gson.annotations.SerializedName

data class WorkspaceResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("about") val about: String? = null,
    @SerializedName("address") val address: String?,
    @SerializedName("features") val features: List<String> = listOf(),
    @SerializedName("image") val image: String? = null,
    @SerializedName("location") val location: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("review") val rate: String? = null,
    @SerializedName("reviews") val reviews: List<Reviews> = listOf(),
    @SerializedName("price_per_hr") val hrPrice: Double = 0.0,
    @SerializedName("type") val type: String = ""
)

data class Reviews(
    @SerializedName("id") val id: Int,
    @SerializedName("description") val comment: String? = null,
    @SerializedName("rating") val rate: Double = 0.0,
    @SerializedName("review") val review: String,
    @SerializedName("user") val user: User,
    @SerializedName("date") val date: String
)

