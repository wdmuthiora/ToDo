package com.example.todo.todo_list

import com.example.todo.data.Todo

sealed class TodoListEvent {

    data class OnDeteleTodo( val todo: Todo ):TodoListEvent()

    data class OnDoneChange( val todo: Todo, val isDone: Boolean ):TodoListEvent()

    object OnUndoDeleteClick: TodoListEvent()

    data class onTodoClick( val todo: Todo ): TodoListEvent()

    object OnAddTodoClick: TodoListEvent()
}