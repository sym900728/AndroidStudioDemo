package com.sym.demogreendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.demogreendao.db.entity.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView testTv;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testTv = (TextView) findViewById(R.id.test_tv);
        this.addNotes();
        this.getNotes();
    }

    private void addNotes() {
        List<Note> notes = new ArrayList<>();
        for(int i = 0; i < 10; i ++) {
            Note note = new Note( (long)i, "note1", "comment1", new Date());
            notes.add(note);
        }
        DbManager.getDaoSession().getNoteDao().insertInTx(notes);
    }

    private void getNotes() {
        List<Note> notes = DbManager.getDaoSession().getNoteDao().loadAll();
        for(Note note : notes) {
            Log.e(TAG, "" + note.getId());
        }
    }
}
