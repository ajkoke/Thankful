package com.example.thankful.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.thankful.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thankful.Model.Notes;
import java.util.List;

public class ThankfulListAdapter extends RecyclerView.Adapter<ThankfulListAdapter.NoteViewHolder> {

    private Context context;
    private List<Notes> notesList;

    public ThankfulListAdapter(Context context, List<Notes> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Notes note = notesList.get(position);
        holder.textTitle.setText(note.getTitle());
        holder.textNote.setText(note.getNotes());
        holder.textDate.setText(note.getDate());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public void setNotes(List<Notes> updatedNotes) {
        this.notesList = updatedNotes;
        notifyDataSetChanged();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textNote, textDate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textNote = itemView.findViewById(R.id.textContent);
            textDate = itemView.findViewById(R.id.textDate);
        }
    }
}
