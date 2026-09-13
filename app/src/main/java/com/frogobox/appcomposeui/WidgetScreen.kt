package com.frogobox.appcomposeui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frogobox.composeui.animation.FrogoAnimationComposeType
import com.frogobox.composeui.animation.frogoAnimationCompose
import com.frogobox.composeui.ext.frogoClickWithFireworksCompose
import com.frogobox.composeui.fireworks.FrogoFireworksCompose
import com.frogobox.composeui.fireworks.rememberFrogoFireworksStateCompose
import com.frogobox.composeui.loadingindicator.FrogoLoadingIndicatorCompose
import com.frogobox.composeui.widget.FrogoAvatar
import com.frogobox.composeui.widget.FrogoBadge
import com.frogobox.composeui.widget.FrogoButton
import com.frogobox.composeui.widget.FrogoCheckbox
import com.frogobox.composeui.widget.FrogoChip
import com.frogobox.composeui.widget.FrogoDivider
import com.frogobox.composeui.widget.FrogoFilterChip
import com.frogobox.composeui.widget.FrogoFloatingActionButton
import com.frogobox.composeui.widget.FrogoIconButton
import com.frogobox.composeui.widget.FrogoOutlinedButton
import com.frogobox.composeui.widget.FrogoOutlinedTextField
import com.frogobox.composeui.widget.FrogoRadioButton
import com.frogobox.composeui.widget.FrogoSpacerLargeHeight
import com.frogobox.composeui.widget.FrogoSpacerMediumHeight
import com.frogobox.composeui.widget.FrogoSpacerSmallHeight
import com.frogobox.composeui.widget.FrogoSwitch
import com.frogobox.composeui.widget.FrogoTextField

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WidgetScreen() {
    val scrollState = rememberScrollState()
    var textValue by remember { mutableStateOf("") }
    var switchState by remember { mutableStateOf(false) }
    var checkboxState by remember { mutableStateOf(false) }
    var radioState by remember { mutableStateOf(true) }
    var selectedChip by remember { mutableStateOf("Compose") }

    // State for interactive animation playground
    var animType by remember { mutableStateOf(FrogoAnimationComposeType.Rubberband) }
    var animTrigger by remember { mutableStateOf(0) }
    var isInfiniteAnim by remember { mutableStateOf(false) }

    // State for Fireworks Canvas
    val fireworksState = rememberFrogoFireworksStateCompose()
    var isEmitting by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // --- BUTTONS SECTION ---
        Text(
            text = "Buttons & Icons",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        FrogoDivider(modifier = Modifier.padding(vertical = 8.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FrogoButton(
                text = "Primary",
                onClick = { /* Action */ },
                icon = Icons.Default.Favorite,
                modifier = Modifier.weight(1f)
            )
            FrogoOutlinedButton(
                text = "Outlined",
                onClick = { /* Action */ },
                modifier = Modifier.weight(1f)
            )
        }
        FrogoSpacerSmallHeight()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FrogoIconButton(icon = Icons.Default.Add, onClick = { /* Action */ })
            FrogoFloatingActionButton(icon = Icons.Default.Add, onClick = { /* Action */ })
            
            // Custom Avatar and Badges
            FrogoBadge(count = 5) {
                FrogoAvatar(
                    initialText = "F",
                    backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    size = 40.dp
                )
            }
            
            FrogoAvatar(
                initialText = "A",
                backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                size = 40.dp
            )
        }
        
        FrogoSpacerLargeHeight()

        // --- TEXT FIELDS SECTION ---
        Text(
            text = "Text Fields",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        FrogoDivider(modifier = Modifier.padding(vertical = 8.dp))
        
        FrogoTextField(
            value = textValue,
            onValueChange = { textValue = it },
            label = "Standard TextField",
            modifier = Modifier.fillMaxWidth()
        )
        FrogoSpacerSmallHeight()
        FrogoOutlinedTextField(
            value = textValue,
            onValueChange = { textValue = it },
            label = "Outlined TextField",
            modifier = Modifier.fillMaxWidth()
        )

        FrogoSpacerLargeHeight()

        // --- SELECTION CONTROLS ---
        Text(
            text = "Selection Controls",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        FrogoDivider(modifier = Modifier.padding(vertical = 8.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FrogoSwitch(checked = switchState, onCheckedChange = { switchState = it })
            FrogoCheckbox(checked = checkboxState, onCheckedChange = { checkboxState = it })
            FrogoRadioButton(selected = radioState, onClick = { radioState = !radioState })
        }
        
        FrogoSpacerSmallHeight()
        
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FrogoChip(label = "Compose Chip", onClick = { selectedChip = "Compose" })
            FrogoFilterChip(
                selected = selectedChip == "Filter",
                onClick = { selectedChip = "Filter" },
                label = "Filter Chip"
            )
            FrogoFilterChip(
                selected = selectedChip == "Showcase",
                onClick = { selectedChip = "Showcase" },
                label = "Showcase Chip"
            )
        }

        FrogoSpacerLargeHeight()

        // --- LOADING INDICATORS (NATIVE CANVAS) ---
        Text(
            text = "Custom Canvas Loading Indicators",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        FrogoDivider(modifier = Modifier.padding(vertical = 8.dp))
        
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        FrogoLoadingIndicatorCompose(indicatorName = "BallPulse", color = MaterialTheme.colorScheme.primary, size = 40.dp)
                        Text("BallPulse", fontSize = 10.sp, modifier = Modifier.padding(top = 4.dp))
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        FrogoLoadingIndicatorCompose(indicatorName = "BallClipRotate", color = MaterialTheme.colorScheme.secondary, size = 40.dp)
                        Text("ClipRotate", fontSize = 10.sp, modifier = Modifier.padding(top = 4.dp))
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        FrogoLoadingIndicatorCompose(indicatorName = "BallScale", color = MaterialTheme.colorScheme.tertiary, size = 40.dp)
                        Text("BallScale", fontSize = 10.sp, modifier = Modifier.padding(top = 4.dp))
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        FrogoLoadingIndicatorCompose(indicatorName = "LineScale", color = MaterialTheme.colorScheme.error, size = 40.dp)
                        Text("LineScale", fontSize = 10.sp, modifier = Modifier.padding(top = 4.dp))
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        FrogoLoadingIndicatorCompose(indicatorName = "Pacman", color = Color(0xFFFDD835), size = 40.dp)
                        Text("Pacman", fontSize = 10.sp, modifier = Modifier.padding(top = 4.dp))
                    }
                }
            }
        }

        FrogoSpacerLargeHeight()

        // --- ATTENTION-SEEKING ANIMATION PLAYGROUND ---
        Text(
            text = "Attention Animation Playground",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        FrogoDivider(modifier = Modifier.padding(vertical = 8.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Animated element wrapper
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .frogoAnimationCompose(
                            type = animType,
                            trigger = animTrigger,
                            durationMillis = 1200,
                            repeat = isInfiniteAnim
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Animate Me! [${animType.name}]",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
                
                FrogoSpacerMediumHeight()
                
                Text("Select Animation Type:", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                FlowRow(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    FrogoAnimationComposeType.values().take(8).forEach { type ->
                        FilterChip(
                            selected = animType == type,
                            onClick = { animType = type },
                            label = { Text(type.name, fontSize = 10.sp) }
                        )
                    }
                }
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { animTrigger++ },
                        modifier = Modifier.weight(1.5f)
                    ) {
                        Text("Trigger Animation", fontSize = 12.sp)
                    }
                    
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isInfiniteAnim,
                            onCheckedChange = { isInfiniteAnim = it }
                        )
                        Text("Infinite", fontSize = 12.sp)
                    }
                }
            }
        }

        FrogoSpacerLargeHeight()

        // --- FIREWORKS CANVAS ---
        Text(
            text = "Interactive Fireworks Canvas",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        FrogoDivider(modifier = Modifier.padding(vertical = 8.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Tap inside the dark box below to trigger pixel-point fireworks particle explosions!",
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                // Fireworks sandbox box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF121212))
                        .border(1.dp, Color.DarkGray, RoundedCornerShape(8.dp))
                        .frogoClickWithFireworksCompose(state = fireworksState) {
                            // Click callback inside the canvas area
                        }
                ) {
                    FrogoFireworksCompose(
                        state = fireworksState,
                        modifier = Modifier.fillMaxSize()
                    )
                    
                    Text(
                        text = "SANDBOX TOUCH AREA",
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }
                
                FrogoSpacerMediumHeight()
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            fireworksState.explode(300f, 300f)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Explode Center", fontSize = 11.sp)
                    }
                    
                    Button(
                        onClick = {
                            isEmitting = !isEmitting
                            if (isEmitting) {
                                fireworksState.startEmit(300f, 150f)
                            } else {
                                fireworksState.stopEmit()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isEmitting) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (isEmitting) "Stop Stream" else "Start Stream", fontSize = 11.sp)
                    }
                }
            }
        }
        
        FrogoSpacerLargeHeight()
    }
}
