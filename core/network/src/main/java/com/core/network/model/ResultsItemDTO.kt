package com.core.network.model

import com.google.gson.annotations.SerializedName

data class ResultsItemDTO(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("species")
    val species: String? = null,

    @field:SerializedName("created")
    val created: String? = null,

    @field:SerializedName("origin")
    val origin: OriginDTO? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("location")
    val location: LocationDTO? = null,

    @field:SerializedName("episode")
    val episode: List<String>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("status")
    val status: String? = null,
)