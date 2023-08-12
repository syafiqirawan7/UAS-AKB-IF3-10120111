//10120111 - Syafiq Pramana Irawan - IF3
package com.example.tugasuas_10120111;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText note_title, note_category, note_description;
    Button save_btn, back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        note_title = findViewById(R.id.note_title);
        note_category = findViewById(R.id.note_category);
        note_description = findViewById(R.id.note_description);
        save_btn = findViewById(R.id.save_btn);
        back_btn = findViewById(R.id.back_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDBHelper db = new MyDBHelper(AddActivity.this);
                db.insertNote(
                        note_title.getText().toString(),
                        note_category.getText().toString(),
                        note_description.getText().toString());

                note_title.setText("");
                note_category.setText("");
                note_description.setText("");
                Toast.makeText(AddActivity.this, "Create data success", Toast.LENGTH_SHORT).show();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}