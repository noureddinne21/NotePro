package com.nouroeddinne.notepro;

import static com.nouroeddinne.notepro.R.menu.menu_option;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import Model.Note;
import MyAdapter.Adapter;
import Utles.Utel;

public class HomeActivity extends AppCompatActivity {
    MyViewModel myViewModel;
    RecyclerView recyclerView;
    static RecyclerView.Adapter adapter;
    ImageView save,setting,home;
    FloatingActionButton fab;
    Context context= this;
    SharedPreferences sharedPreferences;

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
        home = findViewById(R.id.imageView2);
        setting = findViewById(R.id.imageView_setting);
        fab = findViewById(R.id.floatingActionButton);

        save.setImageResource(R.drawable.save_white);
        home.setImageResource(R.drawable.notes_orange);

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences(Utel.SHAREDPREFERNCES_FILENAME_SETTINGS, Context.MODE_PRIVATE);

        loadAllNote();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,Show_EditActivity.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setImageResource(R.drawable.save_white);
                home.setImageResource(R.drawable.notes_orange);
                loadAllNote();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save.setImageResource(R.drawable.save_orange);
                home.setImageResource(R.drawable.notes);
                loadAllNoteSave();
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        for (Note n : noteList){
//            Log.d("TAG", "onCreate: "+n.getId()+" "+n.getTitel()+" "+n.getDate()+" "+n.getFavoraite());
//        }

    }





    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(menu_option,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_bar).getActionView();
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                myViewModel.searchNotes(String.valueOf(query)).observe(HomeActivity.this, new Observer<List<Note>>() {
                    @Override
                    public void onChanged(List<Note> notes) {
                        adapter = new Adapter(context,notes);
                        recyclerView.setAdapter(adapter);
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myViewModel.searchNotes(String.valueOf(newText)).observe(HomeActivity.this, new Observer<List<Note>>() {
                    @Override
                    public void onChanged(List<Note> notes) {
                        adapter = new Adapter(context,notes);
                        recyclerView.setAdapter(adapter);
                    }
                });
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                loadAllNote();
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public void loadAllNote(){

        if (sharedPreferences.getInt(Utel.SHAREDPREFERNCES_SORT_TEXT,-1)!=-1){
            if (sharedPreferences.getInt(Utel.SHAREDPREFERNCES_SORT_TEXT,-1)==1){
                myViewModel.getAllNotesByName().observe(HomeActivity.this, new Observer<List<Note>>() {
                    @Override
                    public void onChanged(List<Note> notes) {
                        adapter = new Adapter(context,notes);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }else{
                myViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
                    @Override
                    public void onChanged(List<Note> notes) {
                        adapter = new Adapter(context,notes);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }

    }


    public void loadAllNoteSave(){

        if (sharedPreferences.getInt(Utel.SHAREDPREFERNCES_SORT_TEXT,-1)!=-1){
            if (sharedPreferences.getInt(Utel.SHAREDPREFERNCES_SORT_TEXT,-1)==1){
                myViewModel.getAllSaveNoteByName().observe(HomeActivity.this, new Observer<List<Note>>() {
                    @Override
                    public void onChanged(List<Note> notes) {
                        adapter = new Adapter(context,notes);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }else{
                myViewModel.getAllSaveNote().observe(this, new Observer<List<Note>>() {
                    @Override
                    public void onChanged(List<Note> notes) {
                        adapter = new Adapter(context,notes);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            new AlertDialog.Builder(HomeActivity.this)
                    .setTitle("Exit")
                    .setMessage("Are you sure you want to Exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, which) -> finish())
                    .setNegativeButton("No", null)
                    .show();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }




















}