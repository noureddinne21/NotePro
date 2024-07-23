package com.nouroeddinne.notepro;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Calendar;
import java.util.Date;

import Model.Note;

public class Show_EditActivity extends AppCompatActivity {
    private TextView date ,charachter;
    private EditText title,note;
    //private DataBaseHendler db;
    private ImageView back,save;
    private int id=-1;
    Date currentDate;



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

        charachter.setText("0");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Note noteExtras = (Note) extras.getSerializable("note");

            id = noteExtras.getId();
            title.setText(noteExtras.getTitel());
            note.setText(noteExtras.getNote());
//            date.setText(DateCoverter.handleSelectedDate(noteExtras.getDate()));
            date.setText(noteExtras.getDate());
            charachter.setText(String.valueOf(note.getText().length()));
        }
        //db = new DataBaseHendler(this);

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
                Intent intent = new Intent(Show_EditActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                Note n = new Note();
                if (title.getText().toString() != null && note.getText().toString() != null && !title.getText().toString().isEmpty()){

                        if(id == -1){
                            //db.addNote(new Note(title.getText().toString(),note.getText().toString(),getDate(),false));
                            n = new Note(title.getText().toString(),note.getText().toString(),"2021/11/21",false);
                        }else {
                            //db.updateNote(new Note(id,title.getText().toString(),note.getText().toString(),getDate()));
                            n = new Note(id,title.getText().toString(),note.getText().toString(),"2021/11/21");
                        }
                        Intent intent = new Intent(Show_EditActivity.this,HomeActivity.class);
                        intent.putExtra("note", n);
                        setResult(RESULT_OK, intent);
                        finish();
                }else {
                    Toast.makeText(Show_EditActivity.this, "Empty Note", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e) {
                Log.d("TAG", "onClick: "+ e.getMessage());
            }


            }
        });


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
























}