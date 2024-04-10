package com.github.rasskazovalexey.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.github.rasskazovalexey.app.ui.ComposeHost

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Storage app",
        state = rememberWindowState(),
    ) {
        ComposeHost()
    }
}
