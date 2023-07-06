package com.example.mytest1.utils

sealed class UIState {
    object Loading: UIState()
    class Success<T>(val response: T):UIState()
    class Error(val error:Exception):UIState()
}