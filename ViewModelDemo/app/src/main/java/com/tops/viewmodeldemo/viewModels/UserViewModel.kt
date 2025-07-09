package com.tops.viewmodeldemo.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tops.viewmodeldemo.model.User

class UserViewModel: ViewModel() {
    private val _users = MutableLiveData<List<User>>()

    val users: LiveData<List<User>> = _users

    init {
        loadUsers()
    }

    private fun loadUsers() {
        _users.value = listOf(
            User(1, "Raj"),
            User(2, "Mit"),
            User(3, "Prit")
        )
    }

    fun addUser(user: User) {
        val updatedList  = _users.value.orEmpty().toMutableList()
        updatedList .add(user)
        _users.value = updatedList
    }
    fun removeUser(id: Int) {
        // existing list = 1, 2,3, 4,5
        // remove id = 3;
        // updated list = 1, 2, 4, 5
        val updatedList  = _users.value.orEmpty().filter { it.id != id }
        _users.value = updatedList
    }
    fun clearUsers() {
        _users.value = emptyList()
    }

}