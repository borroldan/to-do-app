package com.example.to_doapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ToDoListPage(viewModel: ToDoViewModel) {
    val toDoList by viewModel.toDoList.observeAsState()
    var showDialog by remember {mutableStateOf(false)}

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .graphicsLayer {
                if (showDialog) {
                    renderEffect = BlurEffect(10f, 10f)
                }
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            toDoList?.let {
                LazyColumn (
                    content = {
                        itemsIndexed(it) { _: Int, item: ToDo ->
                            ToDoItem(item = item, onDelete = {
                                viewModel.deleteToDo(item.id)
                            })
                        }
                    }
                )
            }?: Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "No items yet",
                fontSize = 20.sp
            )
        }

        Button(
            onClick = {showDialog = true},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(64.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(82.dp)
            )
        }

        if (showDialog) {
            AddToDoDialog(
                onDismiss = {showDialog = false},
                onAdd = {title ->
                    viewModel.addToDo(title)
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun ToDoItem(item : ToDo, onDelete : () -> Unit) {
    Row (
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm:aa, yyyy/MM/dd", Locale.ENGLISH).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.LightGray
            )
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}

@Composable
fun AddToDoDialog(onDismiss: () -> Unit, onAdd: (String) -> Unit) {
    var inputText by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Add New to-do",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            OutlinedTextField(
                value = inputText,
                onValueChange = {inputText = it},
                label = {
                    Text(
                        text = "To-do Title",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        },
        confirmButton = {
            TextButton(onClick = {onAdd(inputText)}) {
                Text(
                    text = "Add",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    )
}
