package com.example.todo.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteTable
import androidx.room.RenameTable
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec

@Database(
    entities = [Todo::class],
    version = 4,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 3,
            to = 4,
            spec = TodoDatabase.AutoMigration::class
        )
    ]
)
abstract class TodoDatabase:RoomDatabase(){
    class AutoMigration : AutoMigrationSpec
    abstract val dao:TodoDao
}

