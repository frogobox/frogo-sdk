package com.frogobox.appcomposeui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frogobox.composeui.template.appbar.FrogoCenterTopAppBar
import com.frogobox.composeui.template.appbar.FrogoLargeTopAppBar
import com.frogobox.composeui.template.appbar.FrogoSearchTopAppBar
import com.frogobox.composeui.template.bottomsheet.FrogoBottomSheet
import com.frogobox.composeui.template.bottomsheet.FrogoListBottomSheet
import com.frogobox.composeui.template.bottomsheet.FrogoMenuBottomSheet
import com.frogobox.composeui.template.bottomsheet.FrogoMenuItem
import com.frogobox.composeui.template.dialog.FrogoAlertDialog
import com.frogobox.composeui.template.dialog.FrogoConfirmDialog
import com.frogobox.composeui.template.dialog.FrogoInputDialog
import com.frogobox.composeui.template.empty.FrogoEmptyView
import com.frogobox.composeui.template.shimmer.FrogoShimmerItem
import com.frogobox.composeui.template.shimmer.FrogoShimmerTextLine
import com.frogobox.composeui.widget.FrogoButton
import com.frogobox.composeui.widget.FrogoDivider
import com.frogobox.composeui.widget.FrogoSpacerHeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateScreen() {
    val scrollState = rememberScrollState()

    // Dialog state
    var showAlertDialog by remember { mutableStateOf(false) }
    var showConfirmDialog by remember { mutableStateOf(false) }
    var showInputDialog by remember { mutableStateOf(false) }
    var dialogResultText by remember { mutableStateOf("No Dialog triggered yet") }

    // Bottom Sheet state
    var showStandardSheet by remember { mutableStateOf(false) }
    var showListSheet by remember { mutableStateOf(false) }
    var showMenuSheet by remember { mutableStateOf(false) }
    var sheetResultText by remember { mutableStateOf("No Bottom Sheet triggered yet") }

    // Search App Bar states
    var searchQuery by remember { mutableStateOf("") }
    var searchActive by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // --- DIALOGS SECTION ---
        Text(
            text = "Custom Dialogs",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        FrogoDivider()
        
        Text(dialogResultText, fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FrogoButton(text = "Alert", onClick = { showAlertDialog = true }, modifier = Modifier.weight(1f))
            FrogoButton(text = "Confirm", onClick = { showConfirmDialog = true }, modifier = Modifier.weight(1f))
            FrogoButton(text = "Input", onClick = { showInputDialog = true }, modifier = Modifier.weight(1f))
        }

        // --- BOTTOM SHEETS SECTION ---
        Text(
            text = "Modal Bottom Sheets",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        FrogoDivider()
        
        Text(sheetResultText, fontSize = 12.sp, color = MaterialTheme.colorScheme.secondary)
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FrogoButton(text = "Standard", onClick = { showStandardSheet = true }, modifier = Modifier.weight(1f))
            FrogoButton(text = "List Sheet", onClick = { showListSheet = true }, modifier = Modifier.weight(1f))
            FrogoButton(text = "Menu Sheet", onClick = { showMenuSheet = true }, modifier = Modifier.weight(1f))
        }

        // --- SHIMMER PLACEHOLDERS ---
        Text(
            text = "Skeleton Shimmer Placeholders",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        FrogoDivider()
        
        Card(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Circle shimmer profile avatar
                FrogoShimmerItem(
                    modifier = Modifier.size(50.dp),
                    shape = RoundedCornerShape(25.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    FrogoShimmerTextLine(width = 150.dp, height = 16.dp)
                    FrogoShimmerTextLine(width = 100.dp, height = 12.dp)
                }
            }
        }

        // --- EMPTY STATE VIEW ---
        Text(
            text = "Empty View Template",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        FrogoDivider()
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            FrogoEmptyView(
                modifier = Modifier.fillMaxSize(),
                title = "PREVIEW NO DATA",
                subtitle = "No items have been loaded.",
                titleColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // --- TOP APP BAR TEMPLATES ---
        Text(
            text = "Top App Bar Styles",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        FrogoDivider()
        
        // Center top app bar mini-mockup
        Card(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text("Center Aligned Style", fontSize = 10.sp, modifier = Modifier.padding(8.dp))
                FrogoCenterTopAppBar(
                    title = "Title Center",
                    navigationIcon = {
                        IconButton(onClick = {}) { Icon(Icons.Default.Menu, null) }
                    },
                    actions = {
                        IconButton(onClick = {}) { Icon(Icons.Default.Share, null) }
                    }
                )
            }
        }
        
        // Large top app bar mini-mockup
        Card(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text("Large App Bar Style", fontSize = 10.sp, modifier = Modifier.padding(8.dp))
                FrogoLargeTopAppBar(
                    title = "Large Title",
                    navigationIcon = {
                        IconButton(onClick = {}) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }
                    },
                    actions = {
                        IconButton(onClick = {}) { Icon(Icons.Default.MoreVert, null) }
                    }
                )
            }
        }

        // Search top app bar mini-mockup
        Card(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text("Search App Bar Style (Click Search Icon to activate)", fontSize = 10.sp, modifier = Modifier.padding(8.dp))
                FrogoSearchTopAppBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    onSearch = { sheetResultText = "Searched: $it" },
                    active = searchActive,
                    onActiveChange = { searchActive = it }
                )
            }
        }

        FrogoSpacerHeight(32.dp)
    }

    // --- Dialog implementations ---
    if (showAlertDialog) {
        FrogoAlertDialog(
            onDismissRequest = { showAlertDialog = false },
            onConfirmation = {
                showAlertDialog = false
                dialogResultText = "Alert acknowledged"
            },
            dialogTitle = "Info Dialog",
            dialogText = "This is a simple alert popup from FrogoComposeUI."
        )
    }

    if (showConfirmDialog) {
        FrogoConfirmDialog(
            onDismissRequest = { showConfirmDialog = false },
            onConfirmation = {
                showConfirmDialog = false
                dialogResultText = "User confirmed action"
            },
            dialogTitle = "Confirm Action",
            dialogText = "Do you want to clear current text values?"
        )
    }

    if (showInputDialog) {
        FrogoInputDialog(
            onDismissRequest = { showInputDialog = false },
            onConfirmation = { input -> 
                showInputDialog = false 
                dialogResultText = "User input: $input"
            },
            dialogTitle = "Profile Name",
            dialogText = "Enter your profile handle:",
            placeholder = "@frogobox"
        )
    }

    // --- Bottom Sheet implementations ---
    if (showStandardSheet) {
        FrogoBottomSheet(
            onDismissRequest = { showStandardSheet = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Standard Bottom Sheet", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text("You can place any custom composable content inside a standard sheet.")
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { showStandardSheet = false }) {
                    Text("Close Sheet")
                }
            }
        }
    }

    if (showListSheet) {
        val sampleItems = listOf("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
        FrogoListBottomSheet(
            onDismissRequest = { showListSheet = false },
            items = sampleItems
        ) { index, item ->
            ListItem(
                headlineContent = { Text("Item $index: $item") },
                modifier = Modifier.clickable {
                    sheetResultText = "Selected list item: $item"
                    showListSheet = false
                }
            )
        }
    }

    if (showMenuSheet) {
        val menuItems = listOf(
            FrogoMenuItem("Edit Profile", Icons.Default.Edit) {
                sheetResultText = "Menu Selected: Edit Profile"
                showMenuSheet = false
            },
            FrogoMenuItem("Share App", Icons.Default.Share) {
                sheetResultText = "Menu Selected: Share App"
                showMenuSheet = false
            }
        )
        FrogoMenuBottomSheet(
            onDismissRequest = { showMenuSheet = false },
            items = menuItems
        )
    }
}
