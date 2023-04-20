package com.core.network.source

import com.core.common.apiresponse.ApiResponse
import com.core.network.apiservice.ApiService
import com.core.network.model.CharacterListResponseDTO
import com.core.network.model.ResultsItemDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : CharacterRemoteDataSource {
    override fun getCharacterList(): Flow<ApiResponse<CharacterListResponseDTO>> = flow {
        try {
            val result = apiService.getCharacterList()
            if (result.isSuccessful && result.body() != null) {
                emit(ApiResponse.Success(result.body()))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: HttpException) {
            when (e.code()) {
                404 -> {
                    emit(ApiResponse.Error(errorMessage = e.message, errorCode = e.code()))
                }

                else -> {
                    emit(ApiResponse.Error(errorMessage = e.message, errorCode = e.code()))
                }
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(errorMessage = e.message, errorCode = null))
        }
    }.flowOn(Dispatchers.IO)

    override fun getSingleCharacter(charId: Int): Flow<ApiResponse<ResultsItemDTO>> = flow {

    }
}