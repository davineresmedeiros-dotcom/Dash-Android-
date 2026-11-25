package com.geode.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.unit.dp
import com.geode.launcher.ui.LaunchScreen

class MainActivity : ComponentActivity() {
    // Load native library "launcherfix" via System.loadLibrary
    // This must be called before any native functions are invoked
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Load the native library early in application lifecycle
        // The library will be loaded from /lib/ directories or via JNI
        try {
            System.loadLibrary("launcherfix")
        } catch (e: UnsatisfiedLinkError) {
            e.printStackTrace()
        }
        setContent {
            MaterialTheme {
                Scaffold(
                    content = {
                        LaunchScreen(
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                )
            }
        }
    }
    // Native function declarations for liblauncherfix.so JNI exports
    // These correspond to extern "C" functions in LauncherFix.cpp
    external fun loadLibrary()
    external fun setDataPath(dataPath: String)
    external fun setOriginalDataPath(dataPath: String)
    external fun enableExceptionsRenaming()
    external fun performPatches()
}
