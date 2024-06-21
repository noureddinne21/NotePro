package Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import Model.Note;
import Utles.Utel;

public class ExDB extends SQLiteAssetHelper {
    public ExDB(Context context) {
        super(context, "car", null, 1);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ Utel.TABLE_NAME);
        onCreate(db);

    }



    public ArrayList<Note> getAll(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<Note> noteList = new ArrayList<Note>();
        String getAll = "SELECT * FROM car";
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


}
