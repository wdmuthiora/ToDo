package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todo.add_edit_todo.AddEditTodoScreen
import com.example.todo.todo_list.TodoListScreen
import com.example.todo.ui.theme.ToDoTheme
import com.example.todo.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {

                val navController = rememberNavController()

                NavHost( navController = navController, startDestination = Routes.TODO_LIST
                ) {

                    composable(Routes.TODO_LIST) {
                        TodoListScreen( onNavigate = { navController.navigate( it.route ) } )
                        val route = navController.currentBackStackEntry?.destination?.route
                        println("+++++++++++++++Route is: " + route)
                    }

                    composable(
                        route = Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                        arguments = listOf(
                            navArgument(name = "todoId"){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){
                        AddEditTodoScreen( onPopBackStack = { navController.popBackStack() } )
                        val route = navController.currentBackStackEntry?.destination?.route
                        println("+++++++++++++++Route is: " + route)
                    }

                }

            }
        }
    }
}
@Composable
fun Title(){
    Text(
        text = "TODO APP",
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp),
        fontSize = 30.sp,
        fontWeight = FontWeight(900),
        style = TextStyle(
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.Cyan, Color.LightGray, Color.Magenta
                )
            )
        )
    )
}