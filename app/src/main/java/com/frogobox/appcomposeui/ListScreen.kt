package com.frogobox.appcomposeui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frogobox.composeui.list.basic.FrogoLazyColumn
import com.frogobox.composeui.list.basic.FrogoLazyRow
import com.frogobox.composeui.list.basic.FrogoListItem
import com.frogobox.composeui.list.basic.FrogoRvGridType1
import com.frogobox.composeui.list.basic.FrogoRvGridType2
import com.frogobox.composeui.list.basic.FrogoRvGridType3
import com.frogobox.composeui.list.basic.FrogoRvGridType4
import com.frogobox.composeui.list.basic.FrogoRvGridType5
import com.frogobox.composeui.list.basic.FrogoRvGridType6
import com.frogobox.composeui.list.basic.FrogoRvGridType7
import com.frogobox.composeui.list.basic.FrogoRvListType1
import com.frogobox.composeui.list.basic.FrogoRvListType10
import com.frogobox.composeui.list.basic.FrogoRvListType11
import com.frogobox.composeui.list.basic.FrogoRvListType12
import com.frogobox.composeui.list.basic.FrogoRvListType2
import com.frogobox.composeui.list.basic.FrogoRvListType3
import com.frogobox.composeui.list.basic.FrogoRvListType4
import com.frogobox.composeui.list.basic.FrogoRvListType5
import com.frogobox.composeui.list.basic.FrogoRvListType6
import com.frogobox.composeui.list.basic.FrogoRvListType7
import com.frogobox.composeui.list.basic.FrogoRvListType8
import com.frogobox.composeui.list.basic.FrogoRvListType9
import com.frogobox.composeui.list.coil.FrogoCoilLazyColumn
import com.frogobox.composeui.list.coil.FrogoCoilListItem
import com.frogobox.composeui.widget.FrogoDivider
import com.frogobox.composeui.widget.FrogoSpacerHeight

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListScreen() {
    val options = listOf("Basic Lists", "List Styles", "Grid Styles", "Image Loaders")
    var selectedOption by remember { mutableStateOf("Basic Lists") }

    Column(modifier = Modifier.fillMaxSize()) {
        // Tab-like Filter chips selection
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            options.forEach { option ->
                FilterChip(
                    selected = selectedOption == option,
                    onClick = { selectedOption = option },
                    label = { Text(option, fontSize = 12.sp) }
                )
            }
        }

        FrogoDivider()

        Box(modifier = Modifier.weight(1f)) {
            when (selectedOption) {
                "Basic Lists" -> BasicListsShowcase()
                "List Styles" -> ListStylesShowcase()
                "Grid Styles" -> GridStylesShowcase()
                "Image Loaders" -> ImageLoadersShowcase()
            }
        }
    }
}

@Composable
fun BasicListsShowcase() {
    val items = List(15) { "ListItem #$it" }
    
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "FrogoLazyRow Preview",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        
        FrogoLazyRow(
            data = items,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) { index, item ->
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 16.dp),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    Text(item, color = MaterialTheme.colorScheme.onPrimaryContainer, fontSize = 12.sp)
                }
            }
        }
        
        FrogoSpacerHeight(16.dp)
        FrogoDivider()
        
        Text(
            text = "FrogoLazyColumn Preview",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        
        FrogoLazyColumn(
            data = items,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) { index, item ->
            FrogoListItem(
                headlineText = item,
                supportingText = "Sub-item details for item #$index",
                onClick = {}
            )
        }
    }
}

@Composable
fun ListStylesShowcase() {
    val scrollState = rememberScrollState()
    val placeholderPainter = painterResource(id = com.frogobox.composeui.R.drawable.frogo_ic_empty_view)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = "FrogoRvList Items (Type 1 to 12)",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        )

        FrogoRvListType1(title = "Type 1: Title Only")
        FrogoRvListType2(title = "Type 2: Title", subtitle = "Subtitle here")
        FrogoRvListType3(title = "Type 3: Title", subtitle = "Subtitle", description = "This is a brief description of the type 3 item layout.")
        
        FrogoRvListType4(title = "Type 4: Image + Title", image = placeholderPainter)
        FrogoRvListType5(title = "Type 5: Image + Title + Subtitle", subtitle = "Subtitle here", image = placeholderPainter)
        FrogoRvListType6(title = "Type 6: Rect Image + Full", subtitle = "Subtitle", description = "This is the full description detailing rect image type 6.", image = placeholderPainter)
        
        FrogoRvListType7(title = "Type 7: Circle Image + Title", image = placeholderPainter)
        FrogoRvListType8(title = "Type 8: Circle Image + Title + Sub", subtitle = "Subtitle", image = placeholderPainter)
        
        FrogoRvListType9(title = "Type 9: Full-width Image + Title Below", image = placeholderPainter)
        FrogoRvListType10(title = "Type 10: Full Image + Title + Sub", subtitle = "Subtitle", image = placeholderPainter)
        FrogoRvListType11(title = "Type 11: Full Image + Title + Sub + Desc", subtitle = "Subtitle", description = "Description text goes here below the image.", image = placeholderPainter)
        
        FrogoRvListType12(title = "Type 12: Title Only Wrap Content")
    }
}

@Composable
fun GridStylesShowcase() {
    val scrollState = rememberScrollState()
    val placeholderPainter = painterResource(id = com.frogobox.composeui.R.drawable.frogo_ic_empty_view)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "FrogoRvGrid Items (Type 1 to 7)",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FrogoRvGridType1(title = "Type 1", image = placeholderPainter)
            }
            Box(modifier = Modifier.weight(1f)) {
                FrogoRvGridType2(title = "Type 2", subtitle = "Subtitle", image = placeholderPainter)
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FrogoRvGridType3(title = "Type 3", subtitle = "Subtitle", description = "Desc text", image = placeholderPainter)
            }
            Box(modifier = Modifier.weight(1f)) {
                FrogoRvGridType4(title = "Type 4 (Circle)", image = placeholderPainter)
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FrogoRvGridType5(title = "Type 5 (Circle)", subtitle = "Sub", image = placeholderPainter)
            }
            Box(modifier = Modifier.weight(1f)) {
                FrogoRvGridType6(title = "Type 6 (Circle)", subtitle = "Sub", description = "Desc", image = placeholderPainter)
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1f)) {
                FrogoRvGridType7(image = placeholderPainter)
            }
            Box(modifier = Modifier.weight(1f)) {
                // Empty spacer matching type 7 grid
            }
        }
    }
}

@Composable
fun ImageLoadersShowcase() {
    val items = List(10) { "User Avatar #$it" }
    // Using static mockup URLs (these point to picsum or standard placeholder images)
    val imageUrl = "https://picsum.photos/200"
    val placeholderPainter = painterResource(id = com.frogobox.composeui.R.drawable.frogo_ic_empty_view)

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Coil / Glide Image Loaders Showcase",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        PrimaryTabRow(selectedTabIndex = 0) {
            Tab(selected = true, onClick = {}, text = { Text("Coil Loader") })
        }

        FrogoCoilLazyColumn(
            data = items,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) { index, item ->
            FrogoCoilListItem(
                imageUrl = imageUrl,
                headlineText = item,
                supportingText = "Loaded dynamically via Coil image extension",
                placeholder = placeholderPainter,
                error = placeholderPainter,
                onClick = {}
            )
        }
    }
}
