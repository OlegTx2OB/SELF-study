package com.example.todoapprecycler.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapprecycler.ToDoItem

@Database(entities = [ToDoItem::class], version = 1)
abstract class AppDataBase : RoomDatabase()
{
    abstract fun toDoDao(): ToDoDao
}