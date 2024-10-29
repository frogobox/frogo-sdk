package com.frogobox.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.frogobox.ui.compose.theme.Purple700
import com.frogobox.ui.compose.theme.dimen_16dp
import com.frogobox.ui.compose.theme.dimen_2dp

/*
 * Created by faisalamir on 09/09/21
 * FrogoAndroidUIKit
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.      
 * All rights reserved
 *
 */

@Composable
fun FrogoComposeListItemType1(
    dataBuilder: DataBuilder,
    clickAble: () -> Unit
) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(dimen_16dp)
            .clickable {
                clickAble()
            },
        elevation = dimen_2dp
    ) {
        Column(
            modifier = Modifier.padding(dimen_16dp)
        ) {
            dataBuilder.textTitle?.let { Text(color = Purple700, text = it, maxLines = 1) }
        }
    }

}