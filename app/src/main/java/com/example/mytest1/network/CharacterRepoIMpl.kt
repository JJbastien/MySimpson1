package com.example.mytest1.network

import android.util.Log
import com.example.mytest1.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepoIMpl @Inject constructor(private val response: ServiceApi) : CharacterRepo {
    override suspend fun getCharacters(): Flow<UIState> {
        return flow{
            emit(UIState.Loading)
           try {
               val service = response.fetchCharacter()
               Log.d("response", "$service")
               if( service.isSuccessful){
                   service.body()?.let{
                       emit(UIState.Success(it))
               }?: throw Exception("no data retried")
               } else {
                   throw Exception("connection failed")
               }
           }catch (e:Exception){
               emit(UIState.Error(e))
           }
        }
    }
}