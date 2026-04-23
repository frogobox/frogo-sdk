package com.frogobox.appcomposeui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.frogobox.composeui.template.appbar.FrogoTopAppBar
import com.frogobox.composeui.template.scaffold.FrogoScaffold
import com.frogobox.composeui.template.tab.FrogoTabRow

@Composable
fun ComposeUiMainScreen() {
    val tabs = listOf("Widgets", "Templates", "Lists")
    var selectedTabIndex by remember { mutableStateOf(0) }

    FrogoScaffold(
        topBar = {
            Column {
                FrogoTopAppBar(title = "Frogo Compose UI Showcase")
                FrogoTabRow(
                    selectedTabIndex = selectedTabIndex,
                    tabs = tabs,
                    onTabSelected = { selectedTabIndex = it }
                )
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            when (selectedTabIndex) {
                0 -> WidgetScreen()
                1 -> TemplateScreen()
                2 -> ListScreen()
            }
        }
    }
}
