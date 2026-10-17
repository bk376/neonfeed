package com.hackernews.futuristic.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.hackernews.futuristic.ui.theme.AppColors
import com.hackernews.futuristic.ui.theme.MonospaceFont

/** Themed filter chip used for feed and search filters. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppChip(label: String, selected: Boolean, onClick: () -> Unit) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = { Text(label, fontFamily = MonospaceFont, fontSize = 12.sp) },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = AppColors.surface,
            labelColor = AppColors.textSecondary,
            selectedContainerColor = AppColors.accent.copy(alpha = 0.18f),
            selectedLabelColor = AppColors.accent
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = AppColors.border,
            selectedBorderColor = AppColors.accent,
            enabled = true,
            selected = selected
        )
    )
}
