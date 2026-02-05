package com.tisto.smartvote

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tisto.helper.core.helper.utils.expect.ProvideBackDispatcher
import com.tisto.smartvote.di.getAppModules
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(getAppModules())
    }) {
        ProvideBackDispatcher {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Hello World",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}