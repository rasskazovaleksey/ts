package com.github.rasskazovalexey.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Immutable
data class StoreScreenUiState(
    val key: String,
    val value: String,
    val log: List<LogEntry>,
) {
    data class LogEntry(
        val message: String,
        val level: Level,
    ) {
        enum class Level {
            INFO,
            WARNING,
            ERROR,
        }
    }

    companion object {
        val default = StoreScreenUiState(
            key = "",
            value = "",
            log = emptyList(),
        )
    }
}

@Composable
fun StoreScreen(
    state: StoreScreenUiState,
    onKeyChanged: (String) -> Unit,
    onValueChanged: (String) -> Unit,
    onGetClicked: () -> Unit,
    onSetClicked: () -> Unit,
    onDelClicked: () -> Unit,
    onCountClicked: () -> Unit,
    onStartClicked: () -> Unit,
    onCommitClicked: () -> Unit,
    onRollbackClicked: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        ControlSection(
            modifier = Modifier.wrapContentSize(),
            key = state.key,
            value = state.value,
            onKeyChanged = onKeyChanged,
            onValueChanged = onValueChanged,
            onGetClicked = onGetClicked,
            onSetClicked = onSetClicked,
            onDelClicked = onDelClicked,
            onCountClicked = onCountClicked,
            onStartClicked = onStartClicked,
            onCommitClicked = onCommitClicked,
            onRollbackClicked = onRollbackClicked,
        )
        LogSection(modifier = Modifier.weight(1F).fillMaxSize(), logs = state.log)
    }
}

@Composable
private fun ControlSection(
    modifier: Modifier = Modifier,
    key: String,
    value: String,
    onKeyChanged: (String) -> Unit,
    onValueChanged: (String) -> Unit,
    onGetClicked: () -> Unit,
    onSetClicked: () -> Unit,
    onDelClicked: () -> Unit,
    onCountClicked: () -> Unit,
    onStartClicked: () -> Unit,
    onCommitClicked: () -> Unit,
    onRollbackClicked: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = onGetClicked) {
                Text("Get")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onSetClicked) {
                Text("Set")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onDelClicked) {
                Text("Del")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onCountClicked) {
                Text("Count")
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = key,
                onValueChange = onKeyChanged,
                label = { Text("Key") },
                singleLine = true,
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                onValueChange = onValueChanged,
                label = { Text("Value") },
                singleLine = true,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(onClick = onStartClicked) {
                Text("Start")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onCommitClicked) {
                Text("Commit")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onRollbackClicked) {
                Text("Rollback")
            }
        }
    }
}

@Composable
private fun LogSection(
    modifier: Modifier = Modifier,
    logs: List<StoreScreenUiState.LogEntry>,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(logs) { log ->
            Row {
                when (log.level) {
                    StoreScreenUiState.LogEntry.Level.INFO ->
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info",
                            tint = MaterialTheme.colorScheme.primary,
                        )

                    StoreScreenUiState.LogEntry.Level.WARNING ->
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Warning",
                            tint = MaterialTheme.colorScheme.secondary,
                        )

                    StoreScreenUiState.LogEntry.Level.ERROR ->
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Error",
                            tint = MaterialTheme.colorScheme.error,
                        )
                }

                Text(
                    text = log.message,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}
