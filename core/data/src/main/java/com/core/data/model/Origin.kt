package com.core.data.model

import com.core.network.model.OriginDTO

data class Origin(
    val name: String? = null,
    val url: String? = null,
)

fun OriginDTO.asDomain(): Origin = Origin(
    name = name,
    url = url
)