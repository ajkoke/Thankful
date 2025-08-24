package com.example.thankful;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thankful.Database.RoomDataB;
import com.example.thankful.Adapters.ThankfulListAdapter;
import com.example.thankful.Model.Notes;
// import com.example.thankful.R;

import java.util.List;

public class PastNotesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ThankfulListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_notes);

        recyclerView = findViewById(R.id.recyclerViewNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadNotes();
    }

    private void loadNotes() {
        List<Notes> notesList = RoomDataB.getInstance(this).MainDAO().getAll();
        adapter = new ThankfulListAdapter(this, notesList);
        recyclerView.setAdapter(adapter);
    }
}
