package com.github.rasskazovalexey.app.ui

import com.github.rasskazovalexey.designsystem.theme.StoreScreenUiState
import com.github.rasskazovalexey.domain.storage.mvi.StorageRedux
import com.github.rasskazovalexey.domain.storage.mvi.core.ViewStateConverter

class StorageStateConverter : ViewStateConverter<StorageRedux.State, StoreScreenUiState> {

    override fun convert(state: StorageRedux.State): StoreScreenUiState {
        return StoreScreenUiState(
            key = state.key,
            value = state.value,
            log = state.log.map {
                StoreScreenUiState.LogEntry(
                    message = it.message,
                    level = when (it.level) {
                        StorageRedux.Message.OnAppendLog.LogLevel.INFO ->
                            StoreScreenUiState.LogEntry.Level.INFO

                        StorageRedux.Message.OnAppendLog.LogLevel.WARNING ->
                            StoreScreenUiState.LogEntry.Level.WARNING

                        StorageRedux.Message.OnAppendLog.LogLevel.ERROR ->
                            StoreScreenUiState.LogEntry.Level.ERROR
                    },
                )
            },
        )
    }
}
