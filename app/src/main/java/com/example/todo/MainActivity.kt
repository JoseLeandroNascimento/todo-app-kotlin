@file:Suppress("UNCHECKED_CAST")

package com.example.todo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo.composables.AppHeader
import com.example.todo.composables.InfoTask
import com.example.todo.composables.TaskCardItem
import com.example.todo.data.Task
import com.example.todo.ui.theme.Gray300
import com.example.todo.ui.theme.TodoTheme
import kotlinx.coroutines.launch


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

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TodoApp(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current.applicationContext as App
    val db = context.db
    val dao = db.taskDao()
    val scope = rememberCoroutineScope()

    var tasks by remember { mutableStateOf(emptyList<Task>()) }

    val totalTask by remember { derivedStateOf { tasks.size } }
    val tasksCompleted by remember { derivedStateOf { tasks.count { it.completed } } }

    LaunchedEffect(Unit) {
        tasks = dao.findAll()
    }

    Scaffold(
        topBar = {
            AppHeader(modifier = Modifier.statusBarsPadding(), onNewTask = {
                scope.launch {
                    val data = Task(name = it, completed = false)
                    dao.save(data)
                    tasks = dao.findAll()
                    Toast.makeText(context, "Tarefa salva com sucesso!", Toast.LENGTH_LONG).show()
                }
            })
        },
    ) { innerPedding ->
        Surface(
            modifier
                .padding(innerPedding)
                .padding(top = 20.dp),
            color = MaterialTheme.colorScheme.background
        ) {

            Column {

                InfoTask(
                    totalTasks = totalTask,
                    totalCompleted = tasksCompleted,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 32.dp)
                        .padding(horizontal = 24.dp)
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 20.dp)
                ) {

                    if (tasks.isEmpty()) {

                        item {
                            PlaceholderListEmpty()
                        }

                    } else {

                        items(tasks) { task ->
                            TaskCardItem(
                                task = task,
                                onDelete = { idTask ->
                                    scope.launch {
                                        dao.delete(idTask)
                                        tasks = dao.findAll()
                                    }
                                    Toast.makeText(
                                        context,
                                        "Tarefa foi deletada!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                },
                                onChangeStatusCompleted = { completed ->
                                    scope.launch {
                                        dao.update(task.copy(completed = completed))
                                        tasks = dao.findAll()
                                    }
                                    val message = if (completed) {
                                        "Tarefa foi concluida"
                                    } else {
                                        "Tarefa não foi concluida"
                                    }
                                    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                                }
                            )
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