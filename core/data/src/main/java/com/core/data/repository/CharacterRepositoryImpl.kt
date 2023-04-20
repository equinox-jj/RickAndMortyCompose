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
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
) : CharacterRepository {
    override fun getCharacterList(): Flow<Results<CharacterListResponse>> = flow {
        emit(Results.Loading)
        try {
            characterRemoteDataSource.getCharacterList().collect() { response ->
                when (response) {
                    is ApiResponse.Success -> {
                        emit(Results.Success(response.data?.asDomain()))
                    }

                    is ApiResponse.Empty -> {
                        emit(Results.Success(null))
                    }

                    is ApiResponse.Error -> {
                        emit(
                            Results.Error(
                                errorMessage = response.errorMessage,
                                errorCode = response.errorCode,
                            )
                        )
                    }
                }
            }
        } catch (e: Exception) {
            emit(Results.Error(errorMessage = e.message, errorCode = null))
        }
    }.flowOn(Dispatchers.Default)

    override fun getSingleCharacter(charId: Int): Flow<Results<ResultsItem>> = flow {

    }
}