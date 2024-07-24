package com.nouroeddinne.notepro;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;
import java.util.Date;

import Model.Note;
import Utles.Utel;

public class Show_EditActivity extends AppCompatActivity {
    private TextView date ,charachter;
    private EditText title,note;
    private ImageView back,save;
    private int id=-1;
    Date currentDate;
    MyViewModel myViewModel;
    SharedPreferences sharedPreferences;

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

        sharedPreferences = getSharedPreferences(Utel.SHAREDPREFERNCES_FILENAME_SETTINGS, Context.MODE_PRIVATE);

        charachter.setText("0");
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Note noteExtras = (Note) extras.getSerializable("note");

            id = noteExtras.getId();
            title.setText(noteExtras.getTitel());
            note.setText(noteExtras.getNote());
            date.setText(DateCoverter.handleSelectedDate(noteExtras.getDate()));
            charachter.setText(String.valueOf(note.getText().length()));
        }

        note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                charachter.setText(String.valueOf(s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sharedPreferences.getBoolean(Utel.SHAREDPREFERNCES_AUTO_SAVE,false)!=false && sharedPreferences.getBoolean(Utel.SHAREDPREFERNCES_AUTO_SAVE,false)){
                    insertNote();
                }else {
                    new AlertDialog.Builder(Show_EditActivity.this)
                            .setTitle("Save")
                            .setMessage("Are you want to save this note?")
                            .setCancelable(false)
                            .setPositiveButton("Save", (dialog, which) -> {
                                // Handle positive button click (Save)
                                insertNote();
                            })
                            .setNegativeButton("No", (dialog, which) -> {
                                // Handle negative button click (No)
                                Intent intent = new Intent(Show_EditActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            })
                            .show();
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertNote();
            }
        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (sharedPreferences.getBoolean(Utel.SHAREDPREFERNCES_AUTO_SAVE,false)!=false && sharedPreferences.getBoolean(Utel.SHAREDPREFERNCES_AUTO_SAVE,false)){
                    insertNote();
            }else {
                new AlertDialog.Builder(Show_EditActivity.this)
                        .setTitle("Save")
                        .setMessage("Are you want to save this note?")
                        .setCancelable(false)
                        .setPositiveButton("Save", (dialog, which) -> {
                            // Handle positive button click (Save)
                            insertNote();
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            // Handle negative button click (No)
                            Intent intent = new Intent(Show_EditActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        })
                        .show();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    private Date pickeDate() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        Calendar selectedCalendar = Calendar.getInstance();
        selectedCalendar.set(year, month, day);
        currentDate= selectedCalendar.getTime();
        return currentDate;
    }


public void insertNote(){

    Note n ;
    if (title.getText().toString() != null && note.getText().toString() != null && !title.getText().toString().isEmpty() && !note.getText().toString().isEmpty()){
        if(id == -1){
            n = new Note(title.getText().toString(),note.getText().toString(),pickeDate(),false);
            myViewModel.insertNote(n);
        }else {
            n = new Note(id,title.getText().toString(),note.getText().toString(),pickeDate());
            myViewModel.updateNote(n);
        }
    }else {
        Toast.makeText(Show_EditActivity.this, "Empty Note", Toast.LENGTH_SHORT).show();
    }

    Intent intent = new Intent(Show_EditActivity.this,HomeActivity.class);
    startActivity(intent);
    finish();

}






















}