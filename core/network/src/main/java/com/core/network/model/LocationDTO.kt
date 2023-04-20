package com.core.network.model

import com.google.gson.annotations.SerializedName

data class LocationDTO(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null,
)