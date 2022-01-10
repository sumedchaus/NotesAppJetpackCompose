package com.example.notesappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import com.example.notesappjetpackcompose.data.NotesDataSource
import com.example.notesappjetpackcompose.models.Note
import com.example.notesappjetpackcompose.screen.NoteScreen
import com.example.notesappjetpackcompose.ui.theme.NotesAppJetpackComposeTheme

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var notes = remember {
                mutableStateListOf<Note>()
            }
            NoteScreen(
                notes = notes,
                onRemovedNote = {
                   notes.remove(it)
                },
                onAddNote = {
                    notes.add(it)
                }
            )
        }
    }
}

