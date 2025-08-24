package com.example.thankful;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thankful.Adapters.NotesAdapter;
import com.example.thankful.Database.RoomDataB;
import com.example.thankful.Model.Notes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import java.util.List;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextNote; //, titleInput, contentInput;
    private Button saveButton;
 //   private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private RoomDataB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Init views
//        titleInput = findViewById(R.id.editTextTitle);
//        contentInput = findViewById(R.id.editTextNote);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextNote = findViewById(R.id.editTextNote);
        saveButton = findViewById(R.id.saveButton);
      /*  recyclerView = findViewById(R.id.recyclerViewNotes); */

        // Initialze database
        db = RoomDataB.getInstance(this);

        // Setup RecyclerView
     //   recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Notes> notesList = db.MainDAO().getAll();
        adapter = new NotesAdapter(notesList, note -> {
            Toast.makeText(this, "Clicked: " + note.getTitle(), Toast.LENGTH_SHORT).show();
        });
      //  recyclerView.setAdapter(adapter);

        // Save button logic
        saveButton.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString().trim();
            String noteContent = editTextNote.getText().toString().trim();
            if (title.isEmpty() && noteContent.isEmpty()) {
                Toast.makeText(this, "Please enter something", Toast.LENGTH_SHORT).show();
                return;
            }
            // Format date
            String currentDate = new SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.getDefault())
                    .format(new Date());
            Notes newNote = new Notes();
            newNote.setTitle(title.isEmpty() ? "Unspecified thanks" : title);
            newNote.setNotes(noteContent);
            //newNote.setDate(String.valueOf(System.currentTimeMillis()));
            newNote.setDate(currentDate);

            db.MainDAO().insert(newNote);

            Toast.makeText(this, "Thankful Note saved!", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish(); // go back to MainActivity
        });
//            String content = contentInput.getText().toString().trim();

 //           if (!title.isEmpty() && !content.isEmpty()) {
 //             //  Notes note = new Notes(title, content, "Today");
 //               Notes note = new Notes();
 //               db.MainDAO().insert(note);

                // Refresh list
//                adapter.updateList(db.MainDAO().getAll());
//               titleInput.setText("");
//                contentInput.setText("");
//            } else {
//                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
