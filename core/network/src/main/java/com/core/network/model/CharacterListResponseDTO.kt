package com.core.network.model

import com.google.gson.annotations.SerializedName

data class CharacterListResponseDTO(

    @field:SerializedName("results")
    val results: List<ResultsItemDTO>? = null,

    @field:SerializedName("info")
    val info: InfoDTO? = null,
)