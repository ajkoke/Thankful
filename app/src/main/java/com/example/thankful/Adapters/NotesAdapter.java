package com.example.thankful.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thankful.Model.Notes;
import com.example.thankful.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Notes> notesList;
    private OnNoteClickListener clickListener;

    // Interface for handling note clicks
    public interface OnNoteClickListener {
        void onNoteClick(Notes note);
    }

    // Constructor
    public NotesAdapter(List<Notes> notesList, OnNoteClickListener listener) {
        this.notesList = notesList;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notes note = notesList.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getNotes());
        holder.date.setText(note.getDate());

        // Handle clicks
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onNoteClick(note);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList != null ? notesList.size() : 0;
    }

    public void updateList(List<Notes> newList) {
    //    this.notesList = newList;
    //    notifyDataSetChanged();
        notesList.clear();
        notesList.addAll(newList);
        notifyDataSetChanged();
    }

    // ViewHolder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textTitle);
            content = itemView.findViewById(R.id.textContent);
            date = itemView.findViewById(R.id.textDate);
        }
    }
}

