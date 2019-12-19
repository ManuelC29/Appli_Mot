package fr.eni.mots.dal;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomWarnings;
import androidx.sqlite.db.SupportSQLiteDatabase;

import fr.eni.mots.model.Liste;
import fr.eni.mots.model.Mot;
import fr.eni.mots.model.Niveau;
import fr.eni.mots.repository.liste.IListeRepository;
import fr.eni.mots.repository.liste.ListeBddRepository;
import fr.eni.mots.repository.mot.IMotRepository;
import fr.eni.mots.repository.mot.MotBddRepository;
import fr.eni.mots.repository.niveau.INiveauRepository;
import fr.eni.mots.repository.niveau.NiveauBddRepository;

@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
@Database(entities = {Mot.class, Liste.class, Niveau.class}, version = 3, exportSchema = false)
public abstract class AppBdd extends RoomDatabase {

    private static AppBdd INSTANCE;

    public abstract ListeDao listeDao();
    public abstract MotDao motDao();
    public abstract NiveauDao niveauDao();

    public synchronized static AppBdd getInstance(Context context) {
        if (INSTANCE == null)
        {
            INSTANCE = Room.databaseBuilder(context, AppBdd.class, "AppBdd")
                    .fallbackToDestructiveMigration()
                    .addCallback(fonctionAppelerApres)
                    .build();
        }
        return INSTANCE;
    }

    private static Callback fonctionAppelerApres = new Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i("XXX","La bdd est créée.");
            new PopulateBddAsync().execute(INSTANCE);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.i("XXX","La bdd est ouverte.");
        }

    };

    private static class PopulateBddAsync extends AsyncTask<AppBdd,Void,Void>
    {

        @Override
        protected Void doInBackground(AppBdd... voids) {
            ListeDao daoList = voids[0].listeDao();
            MotDao motDao = voids[0].motDao();
            NiveauDao niveauDao = voids[0].niveauDao();

            ///////////////   INSERTION DE NIVEAU //////////////////////
            // Création de niveau
            Niveau niv1 = new Niveau(null,"Niveau 1 : Niveau des Petits Joueurs");
            Niveau niv2 = new Niveau(null,"Niveau 2 : Normal, On prend pas de risque");
            Niveau niv3 = new Niveau(null, "Niveau 3 : Hard, On se prend pour une star");;

            // Enregistrement des niveaux
            niveauDao.insert(niv1);
            niveauDao.insert(niv2);
            niveauDao.insert(niv3);

            ///////////////   INSERTION DE LISTES //////////////////////
            Liste list1 = new Liste(null,"Liste 1", 1);
            Liste list2 = new Liste(null,"Liste 2", 1);
            Liste list3 = new Liste(null,"Liste 1", 2);
            Liste list4 = new Liste(null,"Liste 1", 3);

            daoList.insert(list1);
            daoList.insert(list2);
            daoList.insert(list3);
            daoList.insert(list4);

            Mot mot1 = new Mot(null,"ABAT", "abat", "",0,1);
            Mot mot2 = new Mot(null,"ABBE", "abbe", "",0,1);
            Mot mot3 = new Mot(null,"AMIS", "amis", "",0,1);
            Mot mot4 = new Mot(null,"CERF", "cerf", "",0,1);
            Mot mot5 = new Mot(null,"DOOM", "doom", "",0,1);

            Mot mot6 = new Mot(null,"TAXI", "taxi", "",0,2);
            Mot mot7 = new Mot(null,"DUEL", "duel", "",0,2);
            Mot mot8 = new Mot(null,"NEON", "neon", "",0,2);
            Mot mot9 = new Mot(null,"WIFI", "wifi", "",0,2);
            Mot mot10 = new Mot(null,"EXPO", "expo", "",0,2);

            Mot mot11 = new Mot(null,"FUMEE", "fumee", "",0,3);
            Mot mot12 = new Mot(null,"YOGAS", "yogas", "",0,3);
            Mot mot13 = new Mot(null,"POMME", "pomme", "",0,3);
            Mot mot14 = new Mot(null,"POUET", "pouet", "",0,3);
            Mot mot15 = new Mot(null,"SABLE", "sable", "",0,3);

            Mot mot16 = new Mot(null,"ADULTE", "adulte", "",0,4);
            Mot mot17 = new Mot(null,"PINARD", "pinard", "",0,4);
            Mot mot18 = new Mot(null,"JULIEN", "julien", "",0,4);
            Mot mot19 = new Mot(null,"CHEVAL", "cheval", "",0,4);
            Mot mot20 = new Mot(null,"VINYLS", "vinyls", "",0,4);
            Mot mot21 = new Mot(null,"WOLOLO", "wololo", "",0,4);

            motDao.insert(mot1);
            motDao.insert(mot2);
            motDao.insert(mot3);
            motDao.insert(mot4);
            motDao.insert(mot5);
            motDao.insert(mot6);
            motDao.insert(mot7);
            motDao.insert(mot8);
            motDao.insert(mot9);
            motDao.insert(mot10);
            motDao.insert(mot11);
            motDao.insert(mot12);
            motDao.insert(mot13);
            motDao.insert(mot14);
            motDao.insert(mot15);
            motDao.insert(mot16);
            motDao.insert(mot17);
            motDao.insert(mot18);
            motDao.insert(mot19);
            motDao.insert(mot20);
            motDao.insert(mot21);


            return null;
        }

    }

}
