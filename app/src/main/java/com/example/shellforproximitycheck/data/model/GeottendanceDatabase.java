package com.example.shellforproximitycheck.data.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//bump version number if the schema changes
//remove export schema later ***look up Database migrations
@Database(entities = {Student.class, Teacher.class},
        version = 1, exportSchema = false)
public abstract class GeottendanceDatabase extends RoomDatabase {
    public abstract DatabaseDAO getDatabaseDAO();

    private static volatile GeottendanceDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static GeottendanceDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GeottendanceDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GeottendanceDatabase.class, "geottendance_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}