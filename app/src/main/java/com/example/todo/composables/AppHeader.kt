package com.example.todo.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.R
import com.example.todo.ui.theme.Gray700
import com.example.todo.ui.theme.TodoTheme

@Composable
fun AppHeader(
    modifier: Modifier = Modifier,
    onNewTask:(String)-> Unit
) {

    var name by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .background(color = Gray700)
            .fillMaxWidth()
            .padding(top = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(painter = painterResource(R.drawable.logo), contentDescription = null)

            Row(
                modifier = Modifier
                    .offset(y = 20.dp)
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .height(54.dp)
                        .weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                        disabledContainerColor = MaterialTheme.colorScheme.background,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                    ),
                    shape = RoundedCornerShape(6.dp),
                    value = name,
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.label_add_task)
                        )
                    },
                    onValueChange = { name = it}
                )
                Button(
                    modifier = Modifier.size(54.dp),
                    onClick = {
                        onNewTask(name)
                        name = ""
                    },
                    shape = RoundedCornerShape(6.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AddCircleOutline,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppHeaderLightPreview() {
    TodoTheme(
        dynamicColor = false,
        darkTheme = false
    ) {
        AppHeader(onNewTask = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun AppHeaderDarkPreview() {
    TodoTheme(
        dynamicColor = false,
        darkTheme = true
    ) {
        AppHeader(onNewTask = {})
    }
}

