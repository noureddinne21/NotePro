package com.nouroeddinne.notepro;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import Model.ModelSpinner;
import MyAdapter.AdapterSpinner;
import Utles.Utel;


public class SettingsActivity extends AppCompatActivity {
    Spinner spinnerSize,spinnerSort;
    ArrayList<ModelSpinner> listSizeText = new ArrayList<>(Arrays.asList(new ModelSpinner("Small"),new ModelSpinner("Medium"),new ModelSpinner("Large")));
    ArrayList<ModelSpinner> listSortNote = new ArrayList<>(Arrays.asList(new ModelSpinner("Date"),new ModelSpinner("Name")));
    LinearLayout back;
    SharedPreferences sharedPreferences;
    AdapterSpinner adapterSpinner;
    Switch autoSave;
    Button backup,restore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        spinnerSize = findViewById(R.id.spinner_size_text);
        spinnerSort = findViewById(R.id.spinner_sort_note);
        back = findViewById(R.id.linear_back);
        autoSave = findViewById(R.id.switch1);
        backup = findViewById(R.id.buttonBackup);
        restore = findViewById(R.id.buttonRestore);

        RoomBackup backup = new RoomBackup(this);



        sharedPreferences = getSharedPreferences(Utel.SHAREDPREFERNCES_FILENAME_SETTINGS, Context.MODE_PRIVATE);

        if (sharedPreferences.getBoolean(Utel.SHAREDPREFERNCES_AUTO_SAVE,false)!=false){
            autoSave.setChecked(sharedPreferences.getBoolean(Utel.SHAREDPREFERNCES_AUTO_SAVE,false));
        }


        adapterSpinner = new AdapterSpinner(this, listSizeText);
        spinnerSize.setAdapter(adapterSpinner);
        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Utel.SHAREDPREFERNCES_SIZE_TEXT,position);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (sharedPreferences.getInt(Utel.SHAREDPREFERNCES_SIZE_TEXT,-1)!=-1){
            spinnerSize.setSelection(sharedPreferences.getInt(Utel.SHAREDPREFERNCES_SIZE_TEXT,-1));
        }

        adapterSpinner = new AdapterSpinner(this, listSortNote);
        spinnerSort.setAdapter(adapterSpinner);
        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(Utel.SHAREDPREFERNCES_SORT_TEXT,position);
                editor.apply();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (sharedPreferences.getInt(Utel.SHAREDPREFERNCES_SORT_TEXT,-1)!=-1){
            spinnerSort.setSelection(sharedPreferences.getInt(Utel.SHAREDPREFERNCES_SORT_TEXT,-1));
        }

        autoSave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(Utel.SHAREDPREFERNCES_AUTO_SAVE,isChecked);
                editor.apply();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


        backup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });













    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(SettingsActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

























}




















