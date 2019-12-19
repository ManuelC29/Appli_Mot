package fr.eni.mots.dal;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomWarnings;

import fr.eni.mots.model.Liste;
import fr.eni.mots.model.Mot;
import fr.eni.mots.model.Niveau;

@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Database(entities = {Mot.class, Liste.class, Niveau.class}, version = 3, exportSchema = false)
public abstract class AppBdd extends RoomDatabase {

    private static AppBdd INSTANCE;

    public abstract ListeDao listeDao();
    public abstract MotDao motDao();
    public abstract NiveauDao niveauDao();

    public synchronized static AppBdd getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppBdd.class, "AppBdd").build();
        }
        return INSTANCE;
    }
}
