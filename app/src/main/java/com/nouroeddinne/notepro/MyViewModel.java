package com.nouroeddinne.notepro;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import Database.Reposetry;
import Model.Note;

public class MyViewModel extends AndroidViewModel {

    Reposetry reposetry;
    public MyViewModel(@NonNull Application application) {
        super(application);
        reposetry = new Reposetry(application);
    }


    public void insertNote(Note note){
        reposetry.insertNote(note);
    }

    public LiveData<List<Note>> getAllNotes(){
        return reposetry.getAllNotes();
    }

    public LiveData<List<Note>> searchNotes(String text){
        return reposetry.searchNotes(text);
    }

    public void updateNote(Note note){
        reposetry.updateNote(note);
    }

    public LiveData<List<Note>> getAllSaveNote(){
        return reposetry.getAllSaveNote();
    }

    public void deleteNote(int id){
        reposetry.deleteNote(id);

    }


}
