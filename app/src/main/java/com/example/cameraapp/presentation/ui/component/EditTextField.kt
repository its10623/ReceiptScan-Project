package com.example.cameraapp.presentation.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.cameraapp.presentation.ui.theme.Border
import com.example.cameraapp.presentation.ui.theme.Expense
import com.example.cameraapp.presentation.ui.theme.Primary
import com.example.cameraapp.presentation.ui.theme.Shape
import com.example.cameraapp.presentation.ui.theme.TextSub

@Composable
fun EditTextField(
    label: String,
    modifier: Modifier,
) {
    var name by remember { mutableStateOf(label) }
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        modifier = modifier,
        textStyle = MaterialTheme.typography.bodyMedium,
        singleLine = true,
        isError = false,
        shape = Shape.Field,
        colors =
            TextFieldDefaults.colors(
                focusedIndicatorColor = Primary,
                unfocusedIndicatorColor = Border.copy(alpha = 0.4f),
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                errorCursorColor = Expense,
            ),
    )
}