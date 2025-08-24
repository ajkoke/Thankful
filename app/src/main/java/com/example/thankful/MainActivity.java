package com.example.thankful;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.thankful.R;
import com.example.thankful.Adapters.NotesAdapter;
import com.example.thankful.Database.RoomDataB;
// import com.example.thankful.Database.MainDAO;
import com.example.thankful.Model.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
  //  private RoomDataB database;
    private FloatingActionButton fabAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewNotes);
        fabAddNote = findViewById(R.id.fabAddNote);

        // Initialize Room database
        RoomDataB database = RoomDataB.getInstance(this);

        // Load notes from database
        List<Notes> notesList = database.MainDAO().getAll();


        notesAdapter = new NotesAdapter(notesList, new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Notes note) {
                Toast.makeText(MainActivity.this, note.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notesAdapter);

        // FloatingActionButton click
        fabAddNote.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
        });
     /* OLD
      fabAddNote.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Notes newNote = new Notes();
                newNote.setTitle("I am Thankful for?");
                newNote.setNotes("Say what you are thankful for.");
                newNote.setDate(String.valueOf(System.currentTimeMillis()));

                database.MainDAO().insert(newNote);

                // Refresh RecyclerView
                notesAdapter.updateList(database.MainDAO().getAll());

                Toast.makeText(MainActivity.this, "Note added!", Toast.LENGTH_SHORT).show();
            }
        }); */
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Refresh notes from database when returning to MainActivity
        RoomDataB database = RoomDataB.getInstance(this);
        List<Notes> notesList = database.MainDAO().getAll();
        notesAdapter.updateList(notesList);
    }
}