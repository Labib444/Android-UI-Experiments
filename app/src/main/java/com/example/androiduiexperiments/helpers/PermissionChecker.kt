package com.example.androiduiexperiments.helpers

import android.accessibilityservice.AccessibilityService
import android.content.ComponentName
import android.content.Context
import android.provider.Settings
import android.text.TextUtils
import android.util.Log
import kotlinx.coroutines.flow.update

object PermissionChecker {
    fun isAccessibilityServiceEnabled(context: Context, serviceClass: Class<out AccessibilityService>): Boolean {
        val expectedComponentName = ComponentName(context, serviceClass)
        Log.d("Permission", "Expected: $expectedComponentName")

        val enabledServices = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        ) ?: return false

        val colonSplitter = TextUtils.SimpleStringSplitter(':')
        colonSplitter.setString(enabledServices)

        for (component in colonSplitter) {
            val parsedComponent = ComponentName.unflattenFromString(component)
            Log.d("Permission", "Found enabled: $parsedComponent")
            if (expectedComponentName == parsedComponent) {
                return true
            }
        }
        return false
    }
}