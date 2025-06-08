package com.example.androiduiexperiments.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.androiduiexperiments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicBottomBar() {
    val scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior(
        rememberBottomAppBarState()
    )
    BottomAppBar (
//        containerColor = MaterialTheme.colors.primary,
        containerColor = colorResource(R.color.red),
        contentColor = Color.White,
        scrollBehavior = scrollBehavior
    )
    {
        IconButton(onClick = { /* Handle navigation */ }) {
            Icon(Icons.Default.Home, contentDescription = "Home")
        }
        Spacer(Modifier.weight(1f, true)) // Pushes other icons to the end
        IconButton(onClick = { /* Handle search */ }) {
            Icon(Icons.Default.Search, contentDescription = "Search")
        }
        IconButton(onClick = { /* Handle settings */ }) {
            Icon(Icons.Default.Settings, contentDescription = "Settings")
        }
    }
}

@Composable
fun BasicFloationActionBar(){
    FloatingActionButton(onClick = { /* Handle FAB click */ }, backgroundColor = colorResource(R.color.red)) {
        Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
    }
}