package com.core.network.source

import com.core.common.apiresponse.ApiResponse
import com.core.network.model.CharacterListResponseDTO
import com.core.network.model.ResultsItemDTO
import kotlinx.coroutines.flow.Flow

interface CharacterRemoteDataSource {
    fun getCharacterList(): Flow<ApiResponse<CharacterListResponseDTO>>
    fun getSingleCharacter(charId: Int): Flow<ApiResponse<ResultsItemDTO>>
}