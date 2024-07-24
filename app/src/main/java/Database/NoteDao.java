package Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import Model.Note;
import Utles.Utel;

@Dao
public interface NoteDao {

    @Insert
    void insertNote(Note note);

    @Query("SELECT * FROM "+ Utel.TABLE_NAME +" ORDER BY "+Utel.KEY_DATE+" DESC")
    LiveData<List<Note>> getAllNotes();
    @Query("SELECT * FROM "+ Utel.TABLE_NAME +" ORDER BY "+Utel.KEY_TITLE+" ASC")
    LiveData<List<Note>> getAllNotesByName();

    @Query("SELECT * FROM " + Utel.TABLE_NAME + " WHERE " + Utel.KEY_NOTE + " LIKE '%' || :text || '%' OR " + Utel.KEY_TITLE + " LIKE '%' || :text || '%'")
    LiveData<List<Note>> searchNotes(String text);

    @Update
    void updateNote(Note note);

    @Query("SELECT * FROM " + Utel.TABLE_NAME + " WHERE "+Utel.KEY_FAVORITE+"=1 ORDER BY "+Utel.KEY_DATE+" DESC")
    LiveData<List<Note>> getAllSaveNote();

    @Query("SELECT * FROM " + Utel.TABLE_NAME + " WHERE "+Utel.KEY_FAVORITE+"=1 ORDER BY "+Utel.KEY_TITLE+" ASC")
    LiveData<List<Note>> getAllSaveNoteByName();

    @Query("DELETE FROM " + Utel.TABLE_NAME + " WHERE " + Utel.KEY_ID + " = :id")
    void deleteNote(int id);













































}
