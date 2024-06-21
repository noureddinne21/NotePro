package Model;

public class Note {

    private int id;
    private String titel;
    private String note;
    private String date;
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
        this.favoraite = favoraite;
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
