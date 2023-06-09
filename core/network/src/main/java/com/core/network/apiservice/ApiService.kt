package com.core.network.apiservice

import com.core.network.model.CharacterListResponseDTO
import com.core.network.model.ResultsItemDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacterList(): Response<CharacterListResponseDTO>

    @GET("character/{id}")
    suspend fun getSingleCharacter(
        @Path("id") charId: Int,
    ): Response<ResultsItemDTO>

    @GET("character")
    suspend fun getCharacterByName(
        @Query("name") name: String = "",
    ): Response<CharacterListResponseDTO>
}