package com.github.rasskazovalexey.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.rasskazovalexey.designsystem.theme.AppTheme
import com.github.rasskazovalexey.designsystem.theme.StoreScreen
import com.github.rasskazovalexey.designsystem.theme.StoreScreenUiState

class AdminActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                StoreScreen(
                    state = StoreScreenUiState(
                        key = "key",
                        value = "value",
                        log =
                        listOf(
                            StoreScreenUiState.LogEntry(
                                message = "INFO message ".repeat(10),
                                level = StoreScreenUiState.LogEntry.Level.INFO,
                            ),
                            StoreScreenUiState.LogEntry(
                                message = "WARNING message",
                                level = StoreScreenUiState.LogEntry.Level.WARNING,
                            ),
                            StoreScreenUiState.LogEntry(
                                message = "message message ",
                                level = StoreScreenUiState.LogEntry.Level.ERROR,
                            ),
                        ),
                    ),
                    onKeyChanged = {},
                    onValueChanged = {},
                    onGetClicked = {},
                    onSetClicked = {},
                    onDelClicked = {},
                    onCountClicked = {},
                    onStartClicked = {},
                    onCommitClicked = {},
                    onRollbackClicked = {},
                )
            }
        }
    }
}
