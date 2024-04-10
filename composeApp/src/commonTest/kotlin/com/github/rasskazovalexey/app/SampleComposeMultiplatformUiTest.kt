package com.github.rasskazovalexey.app

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import com.github.rasskazovalexey.app.ui.ComposeHost
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class SampleComposeMultiplatformUiTest {
    @Test
    fun givenValueSet_thenValueReceived() =
        runComposeUiTest {
            setContent {
                ComposeHost()
            }

            onNodeWithText("Key").performTextInput("foo")
            onNodeWithText("Value").performTextInput("123")
            onNodeWithText("Set").performClick()

            onNodeWithText("Set value by key 'foo': 123").assertExists()
        }
}
