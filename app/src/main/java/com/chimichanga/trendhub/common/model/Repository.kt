package com.chimichanga.trendhub.common.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repository(
    val id: Int,
    val description: String,
    @SerializedName("full_name") val fullname: String,
    val owner: Owner,
    @SerializedName("html_url") val url: String,
    val language: String?,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("forks_count") val forks: Int
) : Serializable

data class Owner(@SerializedName("avatar_url") val avatarUrl: String) : Serializable