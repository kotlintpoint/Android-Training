package com.tops.viewmodeldemo.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel: ViewModel() {

    private val _count = MutableLiveData<Int>().apply { value = 0 }

    val count:LiveData<Int> = _count

    fun increment() {
        _count.value = (_count.value ?: 0) + 1
    }
    fun decrement() {
        _count.value = (_count.value ?: 0) - 1
    }
    fun reset() {
        _count.value = 0
    }
}