package com.mobile.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mobile.todoapp.data.local.TodoDatabase
import com.mobile.todoapp.data.remote.TodoApi
import com.mobile.todoapp.repository.TodoRepository
import com.mobile.todoapp.ui.navigation.NavGraph
import com.mobile.todoapp.viewmodel.TodoDetailViewModel
import com.mobile.todoapp.viewmodel.TodoListViewModel

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = TodoDatabase.getDatabase(this)
        val api = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
            .create(TodoApi::class.java)
        print(api)

        val repo = TodoRepository(api, db.todoDao())
        val listViewModel = TodoListViewModel(repo)

        setContent {
            NavGraph(
                listViewModel = listViewModel,
                detailViewModelFactory = { TodoDetailViewModel(repo, it) }
            )
        }
    }
}
