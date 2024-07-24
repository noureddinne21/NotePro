package Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import Model.Note;

public class Reposetry {

    NoteDao noteDao;
    public Reposetry(Application application) {
        DatabaseNote databaseNote = DatabaseNote.getDatabase(application);
        noteDao = databaseNote.noteDao();
    }

    public void insertNote(Note note){
        DatabaseNote.executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.insertNote(note);
            }
        });
    }

    public LiveData<List<Note>> getAllNotes(){
        return noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotesByName(){
        return noteDao.getAllNotesByName();
    }

    public LiveData<List<Note>> searchNotes(String text){
        return noteDao.searchNotes(text);
    }

    public void updateNote(Note note){
        DatabaseNote.executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.updateNote(note);
            }
        });
    }

    public LiveData<List<Note>> getAllSaveNote(){
        return noteDao.getAllSaveNote();
    }
    public LiveData<List<Note>> getAllSaveNoteByName(){
        return noteDao.getAllSaveNoteByName();
    }

    public void deleteNote(int id){
        DatabaseNote.executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.deleteNote(id);
            }
        });
    }








































}
