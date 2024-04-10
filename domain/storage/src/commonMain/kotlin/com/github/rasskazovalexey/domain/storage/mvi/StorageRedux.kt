package com.github.rasskazovalexey.domain.storage.mvi

class StorageRedux {
    data class State(
        val key: String,
        val value: String,
        val log: List<LogEntry>,
    ) {

        data class LogEntry(
            val message: String,
            val level: Message.OnAppendLog.LogLevel,
        )

        companion object {
            val default =
                State(
                    key = "",
                    value = "",
                    log = emptyList(),
                )
        }
    }

    sealed class Message {

        data class OnKeyChanged(val key: String) : Message()

        data class OnValueChanged(val value: String) : Message()
        data object OnGetValue : Message()

        data object OnSetValue : Message()

        data object OnDeleteValue : Message()

        data object OnCountValue : Message()

        data object OnBeginTransaction : Message()

        data object OnCommitTransaction : Message()

        data object OnRollbackTransaction : Message()

        data class OnAppendLog(val log: String, val level: LogLevel) : Message() {
            enum class LogLevel {
                INFO,
                WARNING,
                ERROR,
            }
        }
    }

    sealed class Effect {
        data class GetValue(val key: String) : Effect()

        data class SetValue(val key: String, val value: String) : Effect()

        data class DeleteValue(val key: String) : Effect()

        data class CountValue(val value: String) : Effect()

        data object BeginTransaction : Effect()

        data object CommitTransaction : Effect()

        data object RollbackTransaction : Effect()
    }
}
