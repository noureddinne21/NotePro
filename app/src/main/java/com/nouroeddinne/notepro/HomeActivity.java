package com.nouroeddinne.notepro;

import static com.nouroeddinne.notepro.R.menu.menu_option;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import Database.DataBaseHendler;
import Model.Note;
import MyAdapter.Adapter;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    static RecyclerView.Adapter adapter;
    ImageView save,setting;
    FloatingActionButton fab;
    EditText search;
    List<Note> noteList;
    DataBaseHendler db;
    Context context= this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        save = findViewById(R.id.imageView_save);
        setting = findViewById(R.id.imageView_setting);
        fab = findViewById(R.id.floatingActionButton);
        search = findViewById(R.id.editText_search);

        db = new DataBaseHendler(context);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noteList = db.getAllNotes();
        adapter = new Adapter(context,noteList);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,Show_EditActivity.class);
                startActivity(intent);
                finish();
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                noteList = db.searchNotes(String.valueOf(s));
                adapter = new Adapter(context,noteList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    public static void refsh(){
        adapter.notifyDataSetChanged();
    }


public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(menu_option,menu);
        return true;
}

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(context,String.valueOf(item.getItemId()), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }


























}