package com.frogobox.appcomposeui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.frogobox.composeui.widget.FrogoButton
import com.frogobox.composeui.widget.FrogoCard
import com.frogobox.composeui.widget.FrogoCheckbox
import com.frogobox.composeui.widget.FrogoChip
import com.frogobox.composeui.widget.FrogoCircularProgress
import com.frogobox.composeui.widget.FrogoDivider
import com.frogobox.composeui.widget.FrogoFloatingActionButton
import com.frogobox.composeui.widget.FrogoIcon
import com.frogobox.composeui.widget.FrogoIconButton
import com.frogobox.composeui.widget.FrogoLinearProgress
import com.frogobox.composeui.widget.FrogoOutlinedButton
import com.frogobox.composeui.widget.FrogoOutlinedTextField
import com.frogobox.composeui.widget.FrogoRadioButton
import com.frogobox.composeui.widget.FrogoSpacerHeight
import com.frogobox.composeui.widget.FrogoSwitch
import com.frogobox.composeui.widget.FrogoTextField

@Composable
fun WidgetScreen() {
    val scrollState = rememberScrollState()
    var textValue by remember { mutableStateOf("") }
    var switchState by remember { mutableStateOf(false) }
    var checkboxState by remember { mutableStateOf(false) }
    var radioState by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text("Buttons")
        FrogoDivider()
        FrogoSpacerHeight()
        
        FrogoButton(text = "Primary Button", onClick = { /* TODO */ }, icon = Icons.Default.Favorite)
        FrogoSpacerHeight()
        FrogoOutlinedButton(text = "Outlined Button", onClick = { /* TODO */ })
        FrogoSpacerHeight()
        FrogoIconButton(icon = Icons.Default.Add, onClick = { /* TODO */ })
        FrogoSpacerHeight()
        FrogoFloatingActionButton(icon = Icons.Default.Add, onClick = { /* TODO */ })
        FrogoSpacerHeight(32.dp)

        Text("Text Fields")
        FrogoDivider()
        FrogoSpacerHeight()
        
        FrogoTextField(
            value = textValue,
            onValueChange = { textValue = it },
            label = "Standard TextField"
        )
        FrogoSpacerHeight()
        FrogoOutlinedTextField(
            value = textValue,
            onValueChange = { textValue = it },
            label = "Outlined TextField"
        )
        FrogoSpacerHeight(32.dp)

        Text("Selection Controls")
        FrogoDivider()
        FrogoSpacerHeight()
        
        FrogoSwitch(checked = switchState, onCheckedChange = { switchState = it })
        FrogoSpacerHeight()
        FrogoCheckbox(checked = checkboxState, onCheckedChange = { checkboxState = it })
        FrogoSpacerHeight()
        FrogoRadioButton(selected = radioState, onClick = { radioState = !radioState })
        FrogoSpacerHeight()
        FrogoChip(label = "Example Chip", onClick = { /* TODO */ })
        FrogoSpacerHeight(32.dp)

        Text("Indicators")
        FrogoDivider()
        FrogoSpacerHeight()
        
        FrogoCircularProgress()
        FrogoSpacerHeight()
        FrogoLinearProgress()
        FrogoSpacerHeight(32.dp)

        Text("Cards")
        FrogoDivider()
        FrogoSpacerHeight()
        
        FrogoCard(modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("This is a FrogoCard")
                FrogoSpacerHeight()
                FrogoIcon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite")
            }
        }
    }
}
