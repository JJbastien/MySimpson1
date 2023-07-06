package com.example.mytest1.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytest1.network.CharacterRepo
import com.example.mytest1.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val response:  CharacterRepo): ViewModel() {
   private  val _characterLiveData : MutableLiveData<UIState> = MutableLiveData()
    val characterLiveData : LiveData<UIState> get() =_characterLiveData

    init{
        getCharacters()
    }

   private fun getCharacters(){
        CoroutineScope(Dispatchers.IO).launch {
            response.getCharacters().collect{ state ->
                Log.d("viewModelstate","$state")
                _characterLiveData.postValue(state)
            }
        }
    }
}