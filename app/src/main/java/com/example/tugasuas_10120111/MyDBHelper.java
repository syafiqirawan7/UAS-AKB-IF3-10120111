//10120111 - Syafiq Pramana Irawan - IF3
package com.example.tugasuas_10120111;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "uts";
    private static final int DATABASE_VERSION = 4;
    private static final String TABLE_NAME = "notes";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_DESCRIPTION = "description";
    private static final String CREATED_AT = "created_at";
    private static final String UPDATED_AT = "updated_at";
    Context ctx;

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_TITLE + " TEXT," +
                KEY_DESCRIPTION +" TEXT," +
                KEY_CATEGORY +" TEXT," +
                CREATED_AT + " datetime default current_timestamp," +
                UPDATED_AT + " datetime default current_timestamp)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public void insertNote(String title, String category, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues val = new ContentValues();
        val.put(KEY_TITLE, title);
        val.put(KEY_CATEGORY, category);
        val.put(KEY_DESCRIPTION, description);

        long res = db.insert(TABLE_NAME, null, val);

        if(res == -1) {
            Toast.makeText(ctx, "Add data failed !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ctx, "Add data success !", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<NoteModel> getNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<NoteModel> arrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            NoteModel notes = new NoteModel();
            notes.id = cursor.getInt(0);
            notes.title = cursor.getString(1);
            notes.description = cursor.getString(2);
            notes.category = cursor.getString(3);
            notes.created_at = cursor.getString(4);
            notes.updated_at = cursor.getString(5);

            arrayList.add(notes);
        }

        return arrayList;
    }

    public void updateNote(NoteModel note) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());

        ContentValues val = new ContentValues();
        val.put(KEY_TITLE, note.title);
        val.put(KEY_DESCRIPTION, note.description);
        val.put(KEY_CATEGORY, note.category);
        val.put(UPDATED_AT, date);

        db.update(TABLE_NAME, val, KEY_ID + "=" + note.id, null);
    }

    public void deleteNote(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + "=" + id, null);
    }
}
