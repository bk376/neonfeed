package com.hackernews.futuristic.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hackernews.futuristic.ui.theme.AppColors
import com.hackernews.futuristic.ui.theme.AppTheme
import com.hackernews.futuristic.ui.theme.MonospaceFont

@Composable
fun ThemePickerDialog(
    selected: AppTheme,
    onSelect: (AppTheme) -> Unit,
    onDismiss: () -> Unit
) {
    val systemDark = isSystemInDarkTheme()
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = AppColors.surface,
        title = {
            Text("Theme", color = AppColors.textPrimary, fontFamily = MonospaceFont, fontWeight = FontWeight.Bold)
        },
        text = {
            Column {
                AppTheme.entries.forEach { theme ->
                    val palette = theme.palette(systemDark)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelect(theme) }
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        RadioButton(
                            selected = theme == selected,
                            onClick = { onSelect(theme) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = AppColors.accent,
                                unselectedColor = AppColors.textSecondary
                            )
                        )
                        Text(
                            text = theme.displayName,
                            color = AppColors.textPrimary,
                            fontFamily = MonospaceFont,
                            modifier = Modifier.weight(1f)
                        )
                        // Swatch: background with the theme's accent and link colors.
                        Row(
                            modifier = Modifier
                                .background(palette.background, RoundedCornerShape(6.dp))
                                .border(1.dp, AppColors.border, RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 6.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            listOf(palette.accent, palette.positive, palette.link).forEach { color ->
                                Box(modifier = Modifier.size(10.dp).background(color, CircleShape))
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("DONE", color = AppColors.accent, fontFamily = MonospaceFont)
            }
        }
    )
}
