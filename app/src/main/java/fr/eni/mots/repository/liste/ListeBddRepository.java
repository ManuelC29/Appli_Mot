package fr.eni.mots.repository.liste;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import fr.eni.mots.dal.AppBdd;
import fr.eni.mots.dal.ListeDao;
import fr.eni.mots.dal.NiveauDao;
import fr.eni.mots.model.Liste;
import fr.eni.mots.model.Niveau;

public class ListeBddRepository implements IListeRepository {

    private Context context = null;
    private ListeDao dao = null;
    List<Liste> listeList = new ArrayList<>();

    //Constructeur
    public ListeBddRepository(Context context)
    {
        this.context = context;
        dao = AppBdd.getInstance(context).listeDao();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void insert(Liste item) {

        new AsyncTask<Liste,Void,Void>()
        {
            @Override
            protected Void doInBackground(Liste... listes)
            {
                dao.insert(listes[0]);
                return null;
            }
        }.execute(item);

    }

    @Override
    public LiveData<Liste> get(int id) {
        return null;
    }

    @Override
    public LiveData<List<Liste>> get() {
        return null;
    }

    @Override
    public void update(Liste item) {

    }

    @Override
    public void delete(Liste article) {

    }

    @Override
    public void delete(int id) {

    }
}
