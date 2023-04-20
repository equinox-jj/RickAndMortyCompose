package com.core.data.model

import com.core.network.model.ResultsItemDTO

data class ResultsItem(
    val image: String? = null,
    val gender: String? = null,
    val species: String? = null,
    val created: String? = null,
    val origin: Origin? = null,
    val name: String? = null,
    val location: Location? = null,
    val episode: List<String>? = null,
    val id: Int? = null,
    val type: String? = null,
    val url: String? = null,
    val status: String? = null,
)

fun ResultsItemDTO.asDomain(): ResultsItem = ResultsItem(
    image = image,
    gender = gender,
    species = species,
    created = created,
    origin = origin?.asDomain(),
    name = name,
    location = location?.asDomain(),
    episode = episode,
    id = id,
    type = type,
    url = url,
    status = status

)