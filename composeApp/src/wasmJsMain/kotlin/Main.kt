import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.github.rasskazovalexey.app.ui.ComposeHost

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("Storage app", canvasElementId = "ComposeTarget") {
        ComposeHost()
    }
}
