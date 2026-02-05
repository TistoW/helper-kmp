package com.zenenta.helper.core.helper.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenenta.helper.core.helper.ui.theme.TextAppearance
import com.zenenta.helper.core.helper.ui.theme.ZenentaHelperTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import helper.core.helper.generated.resources.Res
import helper.core.helper.generated.resources.hasil_pencarian_tidak_ditemukan
import helper.core.helper.generated.resources.ic_asset_list

@Composable
fun EmptyState(
    title: String,
    subtitle: String,
    search: String = "",
    actionText: String? = null,
    onAction: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    val displaySearch = if (search.length > 10) {
        search.take(10) + "..."
    } else {
        search
    }
    val textSubtitle = if (search.isNotEmpty()) {
        stringResource(
            Res.string.hasil_pencarian_tidak_ditemukan,
            displaySearch
        )
    } else subtitle

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Icon(
            painter = painterResource(Res.drawable.ic_asset_list),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )
        Spacer(Modifier.height(5.dp))
        Text(title, style = TextAppearance.title2(), textAlign = TextAlign.Center)
        Spacer(Modifier.height(5.dp))
        Text(textSubtitle, style = TextAppearance.body2(), textAlign = TextAlign.Center)
        Spacer(Modifier.height(16.dp))
        if (actionText != null) ButtonNormal(text = actionText) { onAction() }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyStatePreview() {
    ZenentaHelperTheme {
        EmptyState(
            title = "No Data",
            subtitle = "There is no data available at the moment.",
            actionText = "Refresh",
            onAction = {}
        )
    }
}