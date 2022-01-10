package com.example.notesappjetpackcompose.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.notesappjetpackcompose.components.NoteButton
import com.example.notesappjetpackcompose.components.NoteInputText
import com.example.notesappjetpackcompose.models.Note
import java.time.format.DateTimeFormatter

@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemovedNote: (Note) -> Unit
) {

    var context = LocalContext.current
    var title by remember {
        mutableStateOf("")
    }
    var description = remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(" Jetpack Notes App")

                },
                actions = {
                    Icon(
                        imageVector = Icons.Rounded.Notifications,
                        contentDescription = "Notification"
                    )
                }
            )
        },

        ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NoteInputText(
                text = title, label = "Title",
                onTextChanged = { title = it },
            )

            NoteInputText(
                text = description.value, label = "Add a Note",
                onTextChanged = { description.value = it },
            )

            Spacer(modifier = Modifier.height(12.dp))
            NoteButton(
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.value.isNotEmpty()) {
                      onAddNote(Note(title =title, description = description.value))
                        title = ""
                        description.value = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }

                },
                modifier = Modifier
            )

            Divider(modifier = Modifier.padding(12.dp))

            LazyColumn {
                items(notes) { note ->
                    NoteRow(note = note,
                    onNoteClicked = {
                        onRemovedNote(note)

                    }
                        )

                }
            }
        }

    }

}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color.LightGray,
        elevation = 6.dp
    ) {
        Column(
            modifier
                .clickable {
                    onNoteClicked(note)
                }
                 .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        )

        {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.caption
            )

        }

    }

}














