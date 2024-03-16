package com.example.navpushdemo

import androidx.compose.runtime.Composable


interface ViewModelInterface {
    @Composable
    fun Run(
        pushViewModel: (ViewModelInterface) -> Unit,
        popViewModel:() -> Unit,
        popToRoot:() -> Unit
    ): Unit
}