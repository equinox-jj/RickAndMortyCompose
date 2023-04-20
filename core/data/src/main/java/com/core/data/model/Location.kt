package com.core.data.model

import com.core.network.model.LocationDTO

data class Location(
    val name: String? = null,
    val url: String? = null,
)

fun LocationDTO.asDomain(): Location = Location(
    name = name,
    url = url,
)