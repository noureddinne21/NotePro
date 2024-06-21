package com.nouroeddinne.notepro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

import Database.DataBaseHendler;
import Model.Note;

public class Show_EditActivity extends AppCompatActivity {
    private TextView date ,charachter;
    private EditText title,note;
    private DataBaseHendler db;
    private ImageView back,save;
    private int id=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        date = findViewById(R.id.textView_show_date);
        charachter = findViewById(R.id.textView_show_charchter);
        title = findViewById(R.id.editText_show_title);
        note = findViewById(R.id.editText_show_note);
        back = findViewById(R.id.imageView_back);
        save = findViewById(R.id.imageView_save_edit);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = Integer.parseInt(extras.getString("id"));
            title.setText(extras.getString("title"));
            note.setText(extras.getString("note"));
            date.setText(extras.getString("date"));
        }
        db = new DataBaseHendler(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Show_EditActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (title.getText().toString() != null && note.getText().toString() != null){
                    if(id == 0){
                        db.addNote(new Note(title.getText().toString(),note.getText().toString(),getDate(),false));
                    }else {
                        db.updatePerson(new Note(id,title.getText().toString(),note.getText().toString(),getDate()));
                    }
                }


                Intent intent = new Intent(Show_EditActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }




    public String getDate(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        String currentDate = String.format("%02d-%02d-%04d", day, month + 1, year);
        return currentDate;
    }



































}