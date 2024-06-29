package Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

import com.nouroeddinne.notepro.Show_EditActivity;

import java.util.ArrayList;
import java.util.List;
import Model.Note;
import Utles.Utel;

public class DataBaseHendler extends SQLiteOpenHelper {

    Context context;
    public DataBaseHendler(@Nullable Context context) {
        super(context, Utel.DATABASE_NAME, null, Utel.DATABASE_VERTION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREAT_T1_TABLE="CREATE TABLE "+Utel.TABLE_NAME+" ("+
                Utel.KEY_ID+" INTEGER PRIMARY KEY,"+
                Utel.KEY_TITLE+" TEXT,"+
                Utel.KEY_NOTE+ " TEXT,"+
                Utel.KEY_DATE+ " TEXT,"+
                Utel.KEY_FAVORITE+" TEXT)";
        db.execSQL(CREAT_T1_TABLE);

        String INSERT_DATA_SQL = "INSERT INTO " + Utel.TABLE_NAME +
                " (" + Utel.KEY_TITLE + ", " + Utel.KEY_NOTE + ", " +
                Utel.KEY_DATE + ", " + Utel.KEY_FAVORITE + ") VALUES " +
                "('First Note', 'HI.', '"+Show_EditActivity.getDate()+"', 'true')";

        db.execSQL(INSERT_DATA_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+Utel.TABLE_NAME);
        onCreate(db);

    }








    public void addNote(Note note){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utel.KEY_TITLE, note.getTitel());
        contentValues.put(Utel.KEY_NOTE, note.getNote());
        contentValues.put(Utel.KEY_DATE, note.getDate());
        contentValues.put(Utel.KEY_FAVORITE, String.valueOf(note.getFavoraite()));
        sqLiteDatabase.insert(Utel.TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
    }


    public List<Note> getAllNotes(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Note> noteList = new ArrayList<Note>();
        String getAll = "SELECT * FROM "+Utel.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(getAll,null);

        if (cursor.moveToFirst())
            do {
                Note note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitel(cursor.getString(1));
                note.setNote(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setFavoraite(Boolean.parseBoolean(cursor.getString(4)));
                noteList.add(note);
            }while (cursor.moveToNext());

        return noteList;

    }

    public List<Note> searchNotes(String text){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        List<Note> noteList = new ArrayList<Note>();
        String query = "SELECT * FROM "+Utel.TABLE_NAME+" WHERE "+ Utel.KEY_NOTE +" LIKE '%" + text + "%' OR "+Utel.KEY_TITLE+" LIKE '%" + text + "%'";

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if (cursor.moveToFirst())
            do {
                Note note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitel(cursor.getString(1));
                note.setNote(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setFavoraite(Boolean.parseBoolean(cursor.getString(4)));
                noteList.add(note);
            }while (cursor.moveToNext());

        return noteList;

    }


    public int updateNote(Note note){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Utel.KEY_TITLE, note.getTitel());
        contentValues.put(Utel.KEY_NOTE, note.getNote());
        contentValues.put(Utel.KEY_DATE, note.getDate());
        contentValues.put(Utel.KEY_FAVORITE, String.valueOf(note.getFavoraite()));
        int result = sqLiteDatabase.update(Utel.TABLE_NAME,contentValues,Utel.KEY_ID+"=?",new String[]{String.valueOf(note.getId())});
        sqLiteDatabase.close();
        return result;
    }


    public ArrayList<Note> getSaveNotes(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Note> dessertList = new ArrayList<Note>();

        Cursor cursor = sqLiteDatabase.query(Utel.TABLE_NAME,
                new String[]{Utel.KEY_ID,Utel.KEY_TITLE,Utel.KEY_NOTE,Utel.KEY_DATE,Utel.KEY_FAVORITE},
                Utel.KEY_FAVORITE + "=?",
                new String[]{String.valueOf("true")}, null, null, null, null);

        if (cursor.moveToFirst())
            do {
                Note note = new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setTitel(cursor.getString(1));
                note.setNote(cursor.getString(2));
                note.setDate(cursor.getString(3));
                note.setFavoraite(Boolean.parseBoolean(cursor.getString(4)));
                dessertList.add(note);
            }while (cursor.moveToNext());

        return dessertList;

    }



//    public int updateFavorite(int id,Boolean status){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(Utel.KEY_FAVORITE,status);
//        int result = sqLiteDatabase.update(Utel.TABLE_NAME,contentValues,Utel.KEY_ID+"=?",new String[]{String.valueOf(id)});
//        sqLiteDatabase.close();
//        Log.d(" update favorite TAG", String.valueOf(result));
//        return result;
//    }


    public int deleteNote(Note note){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = sqLiteDatabase.delete(Utel.TABLE_NAME,Utel.KEY_ID+"=?",new String[]{String.valueOf(note.getId())});
        sqLiteDatabase.close();
        return result;
    }


//    public int numNotes(){
//        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        String getAll = "SELECT * FROM "+Utel.TABLE_NAME;
//        Cursor cursor = sqLiteDatabase.rawQuery(getAll,null);
//        return cursor.getCount();
//    }













}
