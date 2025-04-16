package `in`.kaligotla.deepak.presentation.profile.components

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.google.accompanist.web.*

@Composable
fun ProfileContent(
    padding: PaddingValues,
) {
    var url by remember { mutableStateOf("https://kaligotla.in/pages/home.html") }
    val state = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()
    var textFieldValue by remember(state.content.getCurrentUrl()) {
        mutableStateOf(state.content.getCurrentUrl() ?: "")
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val loadingState = state.loadingState
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = loadingState.progress,
                modifier = Modifier.fillMaxWidth()
            )
        }

        val webClient = remember {
            object : AccompanistWebViewClient() {
                override fun onPageStarted(
                    view: WebView?,
                    url: String?,
                    favicon: Bitmap?
                ) {
                    super.onPageStarted(view, url, favicon)
                }
            }
        }

        WebView(
            state = state,
            modifier = Modifier.weight(1f),
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
            },
            client = webClient
        )
    }
}