package com.example.todo.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todo.data.Task
import com.example.todo.ui.theme.Gray300
import com.example.todo.ui.theme.Gray500

@Composable
fun TaskCardItem(
    modifier: Modifier = Modifier,
    task: Task,
    onDelete: (Int) -> Unit,
    onChangeStatusCompleted: (Boolean) -> Unit
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Gray500
        ),
        shape = RoundedCornerShape(8.dp)
    ) {

        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary,
                    unselectedColor = MaterialTheme.colorScheme.primary,
                ),
                selected = task.completed,
                onClick = {
                    onChangeStatusCompleted(!task.completed)
                }
            )

            Text(
                modifier = Modifier.weight(1f),
                text = task.name,
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = if (task.completed) {
                    TextDecoration.LineThrough
                } else {
                    TextDecoration.None
                },
                color = Color.White,
            )

            IconButton(onClick = { onDelete(task.id) }) {
                Icon(
                    imageVector = Icons.Default.DeleteOutline,
                    contentDescription = null,
                    tint = Gray300
                )
            }
        }

    }
}