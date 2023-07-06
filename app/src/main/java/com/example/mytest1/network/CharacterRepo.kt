package com.example.mytest1.network

import com.example.mytest1.utils.UIState
import kotlinx.coroutines.flow.Flow


interface CharacterRepo {
 suspend  fun getCharacters(): Flow<UIState>
}