package Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.Note;
import Utles.Utel;


@Database(entities = {Note.class},version = Utel.DATABASE_VERTION,exportSchema = true)
public abstract class DatabaseNote extends RoomDatabase {

    public abstract NoteDao noteDao();
    private static volatile DatabaseNote INSTANCE ;
    private static final int NUMBER_OF_THREDS = 4;
    static ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREDS);

    public static DatabaseNote getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseNote.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),DatabaseNote.class, Utel.DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }



    @Override
    public void clearAllTables() {
        runInTransaction(() -> {
            this.query("DELETE FROM " + Utel.TABLE_NAME, null);
        });
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(@NonNull DatabaseConfiguration databaseConfiguration) {
        return null;
    }



}
