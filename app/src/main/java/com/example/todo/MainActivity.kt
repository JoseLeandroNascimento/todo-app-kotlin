package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.composables.AppHeader
import com.example.todo.composables.InfoTask
import com.example.todo.composables.TaskCardItem
import com.example.todo.data.Task
import com.example.todo.ui.theme.Gray300
import com.example.todo.ui.theme.TodoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTheme(dynamicColor = false) {
                TodoApp()
            }
        }
    }
}

@Composable
fun TodoApp(modifier: Modifier = Modifier) {

    var task = listOf<Task>(
        Task(
            name = "Integer urna interdum massa libero auctor neque turpis turpis semper.",
            completed = false
        ),
        Task(
            name = "Integer urna interdum massa libero auctor neque turpis turpis semper.",
            completed = false
        ),
        Task(
            name = "Integer urna interdum massa libero auctor neque turpis turpis semper.",
            completed = false
        ),
        Task(
            name = "Integer urna interdum massa libero auctor neque turpis turpis semper.",
            completed = false
        ),
        Task(
            name = "Integer urna interdum massa libero auctor neque turpis turpis semper.",
            completed = false
        ),
        Task(
            name = "Integer urna interdum massa libero auctor neque turpis turpis semper.",
            completed = false
        ),
        Task(
            name = "Integer urna interdum massa libero auctor neque turpis turpis semper.",
            completed = false
        ),
        Task(
            name = "Integer urna interdum massa libero auctor neque turpis turpis semper.",
            completed = false
        ),
        Task(
            name = "Integer urna interdum massa libero auctor neque turpis turpis semper.",
            completed = false
        ),
        Task(
            name = "Integer urna interdum massa libero auctor neque turpis turpis semper.",
            completed = false
        ),
        Task(
            name = "Integer urna interdum massa libero auctor neque turpis turpis semper.",
            completed = false
        ),
        Task(
            name = "Integer urna interdum massa libero auctor neque turpis turpis semper.",
            completed = false
        )

    )

    Scaffold(
        topBar = {
            AppHeader(modifier = Modifier.statusBarsPadding())
        }
    ) { innerPedding ->
        Surface(
            modifier
                .padding(innerPedding)
                .padding(top = 20.dp),
            color = MaterialTheme.colorScheme.background
        ) {

            Column {

                InfoTask(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .padding(horizontal = 24.dp)
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 20.dp)
                ) {

                    if (task.isEmpty()) {

                        item {
                            PlaceholderListEmpty()
                        }

                    } else {

                        items(task) { task ->
                            TaskCardItem(task = task)
                        }
                    }
                }
            }

        }
    }
}


@Composable
fun PlaceholderListEmpty(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(54.dp),
            painter = painterResource(id = R.drawable.clipboard),
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            text = "Você ainda não tem tarefas cadastradas",
            style = MaterialTheme.typography.titleMedium,
            color = Gray300
        )
        Text(
            text = "Crie tarefas e organize seus itens a fazer",
            style = MaterialTheme.typography.titleSmall,
            color = Gray300
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun TodoAppLightPreview() {
    TodoTheme(
        dynamicColor = false,
        darkTheme = false
    ) {
        TodoApp()
    }
}

@Preview(showBackground = true)
@Composable
private fun TodoAppDarkPreview() {
    TodoTheme(
        dynamicColor = false,
        darkTheme = true
    ) {
        TodoApp()
    }
}