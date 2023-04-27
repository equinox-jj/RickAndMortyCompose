package com.feature.character.characterdetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.core.common.utils.Constants.CHAR_DETAIL_ARG
import com.core.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val characterRepository: CharacterRepository,
) : ViewModel() {

    val charId = savedStateHandle.get<Int>(CHAR_DETAIL_ARG)
//    private val charId = savedStateHandle.getStateFlow(CHAR_DETAIL_ARG, 0)
//    val state = characterRepository.getSingleCharacter(charId)

    init {
        Log.d("Char_Id_Is :", "$charId")
    }
}