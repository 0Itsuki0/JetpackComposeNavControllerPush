package com.example.navpushdemo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


enum class NavigationRoute {
    MAIN
}


class NavigationViewModel: ViewModel() {
    private lateinit var navController: NavHostController
    private var viewModelStack: MutableList<ViewModelInterface> = mutableListOf(MainViewModel())

    private fun pushViewModel(viewModel: ViewModelInterface) {
        if (!this::navController.isInitialized) {
            return
        }
        viewModelStack.add(viewModel)
        navController.navigate(NavigationRoute.MAIN.name)
    }

    private fun popViewModel() {
        if (!this::navController.isInitialized) {
            return
        }
        if (viewModelStack.size == 1) {
            return
        }
        viewModelStack.removeLast()
        navController.navigate(NavigationRoute.MAIN.name)
    }

    private fun popToRoot() {
        if (!this::navController.isInitialized) {
            return
        }
        if (viewModelStack.size == 1) {
            return
        }
        viewModelStack.removeAll(viewModelStack.slice(1..<viewModelStack.size))
        navController.navigate(NavigationRoute.MAIN.name)
    }


    @Composable
    fun Run(){

        navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = NavigationRoute.MAIN.name)
        {

            composable(NavigationRoute.MAIN.name) {
                if (viewModelStack.isEmpty()) {
                    return@composable
                }
                viewModelStack.last().Run(
                    pushViewModel = { pushViewModel(it) },
                    popViewModel = { popViewModel() },
                    popToRoot = { popToRoot() }
                )
            }
        }
    }
}