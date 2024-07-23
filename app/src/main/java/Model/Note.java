package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.nouroeddinne.notepro.DateCoverter;

import java.io.Serializable;
import java.util.Date;

import Utles.Utel;

@Entity(tableName = Utel.TABLE_NAME)
@TypeConverters({DateCoverter.class})
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Utel.KEY_ID)
    private int id;
    @ColumnInfo(name = Utel.KEY_TITLE)
    private String titel;
    @ColumnInfo(name = Utel.KEY_NOTE)
    private String note;
    @ColumnInfo(name = Utel.KEY_DATE)
    private String date;
    @ColumnInfo(name = Utel.KEY_FAVORITE)
    private boolean favoraite;



    public Note(int id, String titel, String note, String date, boolean favoraite) {
        this.id = id;
        this.titel = titel;
        this.note = note;
        this.date = date;
        this.favoraite = favoraite;
    }

    public Note(String titel, String note, String date, boolean favoraite) {
        this.titel = titel;
        this.note = note;
        this.date = date;
        this.favoraite = favoraite;
    }

    public Note(int id, String titel, String note, String date) {
        this.id = id;
        this.titel = titel;
        this.note = note;
        this.date = date;
    }

    public Note(int id, boolean favoraite) {
        this.id = id;
        this.favoraite = favoraite;
    }

    public Note() {
    }


    public boolean getFavoraite() {
        return favoraite;
    }

    public void setFavoraite(boolean favoraite) {
        this.favoraite = favoraite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
