package com.kirara.eroidesu.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.kirara.core.ui.theme.EroidesuTheme
import com.kirara.eroidesu.BuildConfig
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            EroidesuTheme(
                darkTheme = false
            ) {
                Surface {
                    MainApp()
                }
            }
        }
    }
}