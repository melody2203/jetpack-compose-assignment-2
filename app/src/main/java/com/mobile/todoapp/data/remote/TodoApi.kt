package com.mobile.todoapp.data.remote

import com.mobile.todoapp.data.model.Todo

import retrofit2.http.GET

interface TodoApi {
    @GET("todos")
    suspend fun getTodos(): List<Todo>
}
