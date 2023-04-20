package com.core.data.model

import com.core.network.model.InfoDTO

data class Info(
    val next: String? = null,
    val pages: Int? = null,
    val prev: Any? = null,
    val count: Int? = null,
)

fun InfoDTO.asDomain(): Info = Info(
    next = next,
    pages = pages,
    prev = prev,
    count = count,
)