package com.core.data.model

import com.core.network.model.CharacterListResponseDTO

data class CharacterListResponse(
    val results: List<ResultsItem>? = null,
    val info: Info? = null,
)

fun CharacterListResponseDTO.asDomain(): CharacterListResponse = CharacterListResponse(
    results = results?.map { it.asDomain() },
    info = info?.asDomain()
)
