package com.github.rasskazovalexey.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.rasskazovalexey.designsystem.theme.AppTheme
import com.github.rasskazovalexey.designsystem.theme.StoreScreen

private val storageScreenModel = StoreScreenModel()

@Composable
fun ComposeHost() {
    AppTheme {
        val state by storageScreenModel.state.collectAsState()
        StoreScreen(
            state = state,
            onKeyChanged = storageScreenModel::onKeyChanged,
            onValueChanged = storageScreenModel::onValueChanged,
            onGetClicked = storageScreenModel::onGetClicked,
            onSetClicked = storageScreenModel::onSetClicked,
            onDelClicked = storageScreenModel::onDelClicked,
            onCountClicked = storageScreenModel::onCountClicked,
            onStartClicked = storageScreenModel::onStartClicked,
            onCommitClicked = storageScreenModel::onCommitClicked,
            onRollbackClicked = storageScreenModel::onRollbackClicked,
        )
    }
}
