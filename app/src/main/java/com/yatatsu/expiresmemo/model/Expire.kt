package com.yatatsu.expiresmemo.model

import java.util.Date


data class Expire(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val createdAt: Date? = null,
    val expiredAt: Date? = null,
    val status: ExpireStatus = ExpireStatus.ALIVE
)