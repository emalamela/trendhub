package com.chimichanga.trendhub.common.model

data class Repository(
    val id: Int,
    val description: String,
    val fullname: String,
    val owner: Owner,
    val stars: Int,
    val forks: Int
)

data class Owner(val avatarUrl: String)