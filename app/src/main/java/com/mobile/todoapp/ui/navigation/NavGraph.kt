package com.mobile.todoapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.*
import com.mobile.todoapp.ui.screen.TodoDetailScreen
import com.mobile.todoapp.ui.screen.TodoListScreen
import com.mobile.todoapp.viewmodel.TodoDetailViewModel
import com.mobile.todoapp.viewmodel.TodoListViewModel


@Composable
fun NavGraph(
    listViewModel: TodoListViewModel,
    detailViewModelFactory: (Int) -> TodoDetailViewModel
) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "todo_list") {
        composable("todo_list") {
            TodoListScreen(viewModel = listViewModel) {
                navController.navigate("todo_detail/$it")
            }
        }
        composable(
            "todo_detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")!!
            TodoDetailScreen(viewModel = detailViewModelFactory(id)) {
                navController.popBackStack()
            }
        }
    }
}
