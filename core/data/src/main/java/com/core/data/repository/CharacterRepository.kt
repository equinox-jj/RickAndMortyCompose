package com.core.data.repository

import com.core.common.result.Results
import com.core.data.model.CharacterListResponse
import com.core.data.model.ResultsItem
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacterList(): Flow<Results<CharacterListResponse>>
    fun getSingleCharacter(charId: Int): Flow<Results<ResultsItem>>
    fun getCharacterByName(name: String): Flow<Results<CharacterListResponse>>
}