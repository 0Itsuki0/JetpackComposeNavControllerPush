package com.example.navpushdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel(), ViewModelInterface {

    @Composable
    override fun Run(
        pushViewModel: (ViewModelInterface) -> Unit,
        popViewModel:() -> Unit,
        popToRoot:() -> Unit
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp, alignment = Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                "Main"
            )

            Button(onClick = {
                pushViewModel(FirstViewModel())
            }) {
                Text(text = "To First View Model")
            }

            Button(onClick = {
                pushViewModel(SecondViewModel())
            }) {
                Text(text = "To Second View Model")
            }
        }
    }
}