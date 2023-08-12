//10120111 - Syafiq Pramana Irawan - IF3
package com.example.tugasuas_10120111;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomNotesAdapter extends RecyclerView.Adapter<CustomNotesAdapter.MyViewHolder> {

    Context ctx;
    ArrayList<NoteModel> notes;

    public CustomNotesAdapter(Context context, ArrayList<NoteModel> notes) {
        this.ctx = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.notes_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.v_note_id.setText(String.valueOf(notes.get(position).id));
        holder.v_note_title.setText(String.valueOf(notes.get(position).title));
        holder.v_note_category.setText(String.valueOf(notes.get(position).category));
        holder.v_note_description.setText(String.valueOf(notes.get(position).description));
        holder.v_note_created_at.setText(String.valueOf(notes.get(position).created_at));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(notes.get(position).id));
                intent.putExtra("title", String.valueOf(notes.get(position).title));
                intent.putExtra("category", String.valueOf(notes.get(position).category));
                intent.putExtra("description", String.valueOf(notes.get(position).description));
                ctx.startActivity(intent);
            }
        });
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper db = new MyDBHelper(ctx);
                db.deleteNote(notes.get(position).id);
                Toast.makeText(ctx, "Delete succsess", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ctx, MainActivity.class);
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView v_note_id, v_note_category, v_note_title, v_note_created_at, v_note_description;
        LinearLayout mainLayout;
        Button delete_btn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            v_note_id = itemView.findViewById(R.id.v_note_id);
            v_note_category = itemView.findViewById(R.id.v_note_category);
            v_note_title = itemView.findViewById(R.id.v_note_title);
            v_note_created_at = itemView.findViewById(R.id.v_note_created_at);
            v_note_description = itemView.findViewById(R.id.v_note_description);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            delete_btn = itemView.findViewById(R.id.delete_btn);
        }
    }
}
