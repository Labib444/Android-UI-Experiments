package com.example.androiduiexperiments.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FireBaseConnectScreen(navController: NavController, userId: String?) {
    val isButtonPressed : Boolean by remember { mutableStateOf(false) }
    val isConnected : Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = {}){
            Text(text = "Connect to Firebase")
        }
        if(isButtonPressed){
            Text(text = if(isConnected) "Successfully Connected!" else "Failed to Connect!")
        }
    }
}