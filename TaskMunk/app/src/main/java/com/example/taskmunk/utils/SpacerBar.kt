package com.example.taskmunk.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SpacerBar(
    height: Int = 4,
    width: Int = 200,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Spacer(
        modifier = Modifier
            .width(width.dp)
            .height(height.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(color)
    )
}