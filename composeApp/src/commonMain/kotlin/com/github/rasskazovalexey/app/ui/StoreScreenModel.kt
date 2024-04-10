package com.github.rasskazovalexey.app.ui

import com.github.rasskazovalexey.data.keyvalue.TransactionalKeyValueStoreComponent
import com.github.rasskazovalexey.designsystem.theme.StoreScreenUiState
import com.github.rasskazovalexey.domain.storage.mvi.StorageEffectHandler
import com.github.rasskazovalexey.domain.storage.mvi.StorageReducer
import com.github.rasskazovalexey.domain.storage.mvi.StorageRedux
import com.github.rasskazovalexey.domain.storage.mvi.core.pure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StoreScreenModel(
    handler: StorageEffectHandler =
        StorageEffectHandler(
            keyValueStore = TransactionalKeyValueStoreComponent.createKeyValueStore(),
            transactionalStore = TransactionalKeyValueStoreComponent.createTransactionalStore(),
        ),
    reducer: StorageReducer = StorageReducer(),
    converter: StorageStateConverter = StorageStateConverter(),
) {
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val mutableState: MutableStateFlow<StoreScreenUiState> = MutableStateFlow(StoreScreenUiState.default)
    val state: StateFlow<StoreScreenUiState> = mutableState.asStateFlow()

    private val engine =
        LaunchReduxEngine(
            initial = StorageRedux.State.default.pure(),
            reducer = reducer,
            effectHandler = handler,
            scope = scope,
        )

    init {
        scope.launch {
            engine.output.collect { state ->
                mutableState.value = converter.convert(state)
            }
        }
    }

    fun onDisposed() {
        scope.cancel()
    }

    fun onKeyChanged(s: String) {
        engine.send(StorageRedux.Message.OnKeyChanged(s))
    }

    fun onValueChanged(s: String) {
        engine.send(StorageRedux.Message.OnValueChanged(s))
    }

    fun onGetClicked() {
        engine.send(StorageRedux.Message.OnGetValue)
    }

    fun onSetClicked() {
        engine.send(StorageRedux.Message.OnSetValue)
    }

    fun onDelClicked() {
        engine.send(StorageRedux.Message.OnDeleteValue)
    }

    fun onCountClicked() {
        engine.send(StorageRedux.Message.OnCountValue)
    }

    fun onStartClicked() {
        engine.send(StorageRedux.Message.OnBeginTransaction)
    }

    fun onCommitClicked() {
        engine.send(StorageRedux.Message.OnCommitTransaction)
    }

    fun onRollbackClicked() {
        engine.send(StorageRedux.Message.OnRollbackTransaction)
    }
}
