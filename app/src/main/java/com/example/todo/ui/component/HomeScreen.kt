package com.example.todo.ui.component

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todo.MainActivity
import com.example.todo.data.model.TodoEntity
import com.example.todo.ui.theme.Dark200
import com.example.todo.ui.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun HomeScreen(viewModel: TodoViewModel = viewModel(), context: Context) {
    val todos by viewModel.todo.collectAsState()
    val (dialogOpen, setDialog) = remember {
        mutableStateOf(false)
    }
    val (title, setTitle) = remember {
        mutableStateOf("")
    }
    val (subtitle, setSubtitle) = remember {
        mutableStateOf("")
    }
    if (dialogOpen) {
        Dialog(onDismissRequest = { setDialog(false) }) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Dark200)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(value = title, onValueChange = {
                    setTitle(it)
                }, modifier = Modifier.fillMaxWidth(), label = {
                    Text(text = "Title", color = Color.White)
                }, colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    textColor = Color.White
                )
                )
                Spacer(modifier = Modifier.height(4.dp))

                OutlinedTextField(value = subtitle, onValueChange = {
                    setSubtitle(it)
                }, modifier = Modifier.fillMaxWidth(), label = {
                    Text(text = "Subtitle", color = Color.White)
                }, colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    textColor = Color.White
                )
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    onClick = {
                        if (title.isNotEmpty() && subtitle.isNotEmpty()) {
                            viewModel.addTodo(TodoEntity(title = title, subTitle = subtitle))
                            setDialog(false)
                        } else {
                            Toast.makeText(
                                context,
                                "Please complete all the oaths",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Submit", color = Color.White)
                }
            }
        }
    }
    Scaffold(containerColor = MaterialTheme.colorScheme.secondary, floatingActionButton = {
        FloatingActionButton(
            onClick = { setDialog(true) },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
        }
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = todos.isEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                Text(text = "No Todos Yet!!", color = Color.White, fontSize = 22.sp)
            }
            AnimatedVisibility(
                visible = todos.isNotEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = paddingValues.calculateBottomPadding() + 8.dp,
                            top = 8.dp,
                            end = 8.dp,
                            start = 8.dp
                        ), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(todos.sortedBy { it.done }, key = { item ->
                        item.id
                    }) { todo ->
                        TodoListItem(todoEntity = todo, onClick = {
                            viewModel.updateTodo(todo.copy(done = !todo.done))
                        }, onDelete = {
                            viewModel.deleteTodo(todo)
                        })
                    }
                }
            }
        }
    }
}



