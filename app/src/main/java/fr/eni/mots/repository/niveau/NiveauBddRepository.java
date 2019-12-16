package fr.eni.mots.repository.niveau;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;



import java.util.ArrayList;
import java.util.List;

import fr.eni.mots.dal.AppBdd;
import fr.eni.mots.dal.NiveauDao;
import fr.eni.mots.model.Niveau;

public class NiveauBddRepository implements INiveauRepository{

    private Context context = null;
    private NiveauDao dao = null;
    List<Niveau> listeNiveau = new ArrayList<>();

    public NiveauBddRepository(Context context)
    {
        this.context = context;
        dao = AppBdd.getInstance(context).niveauDao();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void insert(Niveau item) {
        new AsyncTask<Niveau,Void,Void>()
        {
            @Override
            protected Void doInBackground(Niveau... niveaux)
            {
                dao.insert(niveaux[0]);
                return null;
            }
        }.execute(item);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Niveau get(int id) {

        return dao.get(id);

    }

    @Override
    public List<Niveau> get() {
        return null;
    }

    @Override
    public void update(Niveau item) {

    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void delete(Niveau niveau) {
        new AsyncTask<Niveau,Void,Void>()
        {
            @Override
            protected Void doInBackground(Niveau... niveaux)
            {
                dao.delete(niveaux[0]);
                return null;
            }
        }.execute(niveau);

    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void delete(int id) {
        new AsyncTask<Integer,Void,Void>()
        {
            @Override
            protected Void doInBackground(Integer... integers)
            {
                dao.delete(integers[0]);
                return null;
            }
        }.execute(id);

    }
}
