package com.example.androiduiexperiments.state

import android.accessibilityservice.AccessibilityService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.text.TextUtils
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class AccessibilityPermissionScreenState (
    val isButtonPressed: Boolean = false,
    val switchOn: Boolean = false,
)

sealed class UiEvent {
    object RequestAccessibilityPermission : UiEvent()
    object AccessibilityPermissionDisabled : UiEvent()
}

class AccessibilityPermissionScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AccessibilityPermissionScreenState())
    val uiState: StateFlow<AccessibilityPermissionScreenState> = _uiState

    private val _event = MutableSharedFlow<UiEvent>()
    val event = _event.asSharedFlow()

    init {
        initViewModel()
    }

    private fun initViewModel(){
        viewModelScope.launch {
            _uiState.update { it.copy(isButtonPressed = false, switchOn = false) }
        }
    }

    fun switchToggled(isChecked: Boolean){
        viewModelScope.launch {
            if(isChecked){
                _event.emit(UiEvent.RequestAccessibilityPermission)
            }else{
                _event.emit(UiEvent.AccessibilityPermissionDisabled)
            }
        }
    }

    fun setSwitchOn(value: Boolean){
        _uiState.update { it.copy(switchOn = value) }
    }

}