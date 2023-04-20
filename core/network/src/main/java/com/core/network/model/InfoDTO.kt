package com.core.network.model

import com.google.gson.annotations.SerializedName

data class InfoDTO(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("pages")
	val pages: Int? = null,

	@field:SerializedName("prev")
	val prev: Any? = null,

	@field:SerializedName("count")
	val count: Int? = null,
)