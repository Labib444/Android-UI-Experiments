package com.example.androiduiexperiments.ui.screens

import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.DisposableEffectScope
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.androiduiexperiments.state.AccessibilityPermissionScreenViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiduiexperiments.helpers.PermissionChecker
import com.example.androiduiexperiments.service.MyAccessibilityService
import com.example.androiduiexperiments.state.UiEvent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccessibilityPermissionScreen(viewModel: AccessibilityPermissionScreenViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        val isEnabled = PermissionChecker.isAccessibilityServiceEnabled(context, MyAccessibilityService::class.java)
        Log.e("Permission", "isEnabled = ${isEnabled}")
        if(isEnabled) viewModel.setSwitchOn(true) else viewModel.setSwitchOn(false)
        viewModel.event.collect { event ->
            when(event){
                is UiEvent.RequestAccessibilityPermission -> {
                    context.startActivity(
                        Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                    )
                }
                is UiEvent.AccessibilityPermissionDisabled -> {
                    context.startActivity(
                        Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                    )
                }
            }
        }
    }

    DisposableEffect(lifecycleOwner){
        val observer = LifecycleEventObserver { _, event ->
            if(event == Lifecycle.Event.ON_RESUME){
                val isEnabled = PermissionChecker.isAccessibilityServiceEnabled(context, MyAccessibilityService::class.java)
                Log.e("Permission", "isEnabled = ${isEnabled}")
                if(isEnabled) viewModel.setSwitchOn(true) else viewModel.setSwitchOn(false)
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column (
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Accessibility Permission", modifier = Modifier.fillMaxWidth().padding(8.dp))
        Switch(checked = uiState.switchOn, enabled = true, onCheckedChange = { isChecked -> viewModel.switchToggled(isChecked) })
    }
}
