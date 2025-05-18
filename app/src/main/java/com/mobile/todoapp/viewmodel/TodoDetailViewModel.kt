package com.mobile.todoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.todoapp.data.model.Todo
import com.mobile.todoapp.repository.TodoRepository

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class TodoDetailViewModel(
    private val repository: TodoRepository,
    private val todoId: Int
) : ViewModel() {
    private val _todo = MutableStateFlow<Todo?>(null)
    val todo: StateFlow<Todo?> = _todo

    init {
        viewModelScope.launch {
            _todo.value = repository.getTodoById(todoId)
        }
    }
}
