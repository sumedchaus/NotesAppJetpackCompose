package com.example.notesappjetpackcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notesappjetpackcompose.models.Note
import com.example.notesappjetpackcompose.util.DateConverter
import com.example.notesappjetpackcompose.util.UUIDConverter


@Database(entities = [Note::class,], version = 1, exportSchema = false )
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao():NoteDatabaseDao
}