package com.example.todo.add_edit_todo

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todo.Title
import com.example.todo.util.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTodoViewModel = hiltViewModel()
) {
    val scaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(key1 = true) {

        viewModel.uiEvent.collect { event ->

            when (event){

                is UiEvent.PopBackStack -> onPopBackStack()

                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }

                else -> Unit

            }
        }

    }
    Scaffold(
       modifier = Modifier
           .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(AddEditTodoEvent.OnSaveTodoClick) }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Saved")
            }    
        }
    ) {
        Title()
        Column(modifier = Modifier.offset(0.dp, 48.dp).padding(16.dp)
            .fillMaxSize()
        ) {

            TextField(
                value = viewModel.title,
                onValueChange = { viewModel
                    .onEvent( AddEditTodoEvent.OnTitleChange(it) )
                },
                placeholder = { Text(text = "Title")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.description,
                onValueChange = { viewModel
                    .onEvent( AddEditTodoEvent.OnDescriptionChange(it) )
                },
                placeholder = { Text(text = "Description")},
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )
        }
    }

}

