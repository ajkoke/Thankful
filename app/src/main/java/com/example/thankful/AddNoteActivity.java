package com.example.thankful.Adapters;

import android.content.Context;
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

    private Context context;
    private List<Notes> notesList;

    public NotesAdapter(Context context, List<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notes note = notesList.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getNotes());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.noteTitle);
            content = itemView.findViewById(R.id.noteContent);
        }
    }

    // <-- Add this method here inside NotesAdapter
    public void updateList(List<Notes> newList) {
        this.notesList = newList;
        notifyDataSetChanged();
    }
}
