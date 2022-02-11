package com.muse.wprk.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.muse.wprk.core.exts.parse

@Composable
fun UniversalTextField(
    title: String,
    icon: ImageVector,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(Modifier.padding(16.dp)) {
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            Modifier.height(65.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                cursorColor = Color.parse("#1C1B1F"),
                disabledLabelColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Gray,
                textColor = Color.Black
            ),
            label = {
                Text(text = title, color = Color.parse("#1C1B1F"))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = Color.parse(
                        "#1C1B1F"
                    )
                )
            }
        )
    }
}

