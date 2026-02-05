package com.zenenta.smartvote

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zenenta.helper.core.helper.utils.expect.ProvideBackDispatcher
import com.zenenta.smartvote.di.getAppModules
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(getAppModules())
    }) {
        ProvideBackDispatcher {
            Box {
                Text(
                    text = "Hello World",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}