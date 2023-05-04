package com.core.network.source

import com.core.common.apiresponse.ApiResponse
import com.core.network.apiservice.ApiService
import com.core.network.model.CharacterListResponseDTO
import com.core.network.model.ResultsItemDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : CharacterRemoteDataSource {
    override fun getCharacterList(): Flow<ApiResponse<CharacterListResponseDTO>> = flow {
        val response = apiService.getCharacterList()
        if (response.isSuccessful && response.body() != null) {
            emit(ApiResponse.Success(response.body()))
        } else {
            emit(ApiResponse.Empty)
        }
    }.flowOn(Dispatchers.IO)

    override fun getSingleCharacter(charId: Int): Flow<ApiResponse<ResultsItemDTO>> = flow {
        val response = apiService.getSingleCharacter(charId)
        if (response.isSuccessful && response.body() != null) {
            emit(ApiResponse.Success(response.body()))
        } else {
            emit(ApiResponse.Empty)
        }
    }.flowOn(Dispatchers.IO)

    override fun getCharacterByName(query: String): Flow<ApiResponse<CharacterListResponseDTO>> =
        flow {
            val response = apiService.getCharacterByName(query)
            if (response.isSuccessful && response.body() != null) {
                emit(ApiResponse.Success(response.body()))
            } else {
                emit(ApiResponse.Empty)
            }
        }.flowOn(Dispatchers.IO)
}