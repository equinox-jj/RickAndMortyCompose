package com.core.data.repository

import com.core.common.apiresponse.ApiResponse
import com.core.common.result.Results
import com.core.data.model.CharacterListResponse
import com.core.data.model.ResultsItem
import com.core.data.model.asDomain
import com.core.network.source.CharacterRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
) : CharacterRepository {
    override fun getCharacterList(): Flow<Results<CharacterListResponse>> = flow {
        emit(Results.Loading)
        try {
            characterRemoteDataSource.getCharacterList().collect { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        emit(Results.Success(response.data?.asDomain()))
                    }

                    is ApiResponse.Empty -> {
                        emit(Results.Success(null))
                    }
                }
            }
        } catch (e: HttpException) {
            emit(Results.Error(errorMessage = e.localizedMessage, errorCode = e.code()))
        } catch (e: Exception) {
            emit(Results.Error(errorMessage = e.localizedMessage, errorCode = null))
        }
    }.flowOn(Dispatchers.Default)

    override fun getSingleCharacter(charId: Int): Flow<Results<ResultsItem>> = flow {
        emit(Results.Loading)
        try {
            characterRemoteDataSource.getSingleCharacter(charId).collect { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        emit(Results.Success(response.data?.asDomain()))
                    }

                    is ApiResponse.Empty -> {
                        emit(Results.Success(null))
                    }
                }
            }
        } catch (e: HttpException) {
            emit(Results.Error(errorMessage = e.localizedMessage, errorCode = e.code()))
        } catch (e: Exception) {
            emit(Results.Error(errorMessage = e.localizedMessage, errorCode = null))
        }
    }.flowOn(Dispatchers.Default)

    override fun getCharacterByName(name: String): Flow<Results<CharacterListResponse>> = flow {
        emit(Results.Loading)
        try {
            characterRemoteDataSource.getCharacterByName(name).collect { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        emit(Results.Success(response.data?.asDomain()))
                    }

                    is ApiResponse.Empty -> {
                        emit(Results.Success(null))
                    }
                }
            }
        } catch (e: HttpException) {
            emit(Results.Error(errorMessage = e.localizedMessage, errorCode = e.code()))
        } catch (e: Exception) {
            emit(Results.Error(errorMessage = e.localizedMessage, errorCode = null))
        }
    }.flowOn(Dispatchers.Default)
}