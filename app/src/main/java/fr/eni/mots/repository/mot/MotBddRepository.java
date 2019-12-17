package fr.eni.mots.repository.mot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import fr.eni.mots.dal.AppBdd;
import fr.eni.mots.dal.ListeDao;
import fr.eni.mots.dal.MotDao;
import fr.eni.mots.model.Liste;
import fr.eni.mots.model.Mot;

public class MotBddRepository implements IMotRepository{
    private Context context = null;
    private MotDao dao = null;
    List<Liste> motList = new ArrayList<>();

    //Constructeur
    public MotBddRepository(Context context)
    {
        this.context = context;
        dao = AppBdd.getInstance(context).motDao();
    }
    @SuppressLint("StaticFieldLeak")
    @Override
    public void insert(Mot item) {
        new AsyncTask<Mot,Void,Void>()
        {
            @Override
            protected Void doInBackground(Mot... mots)
            {
                dao.insert(mots[0]);
                return null;
            }
        }.execute(item);
    }

    @Override
    public LiveData<Mot> get(int id) {
        return null;
    }

    @Override
    public LiveData<List<Mot>> get() {
        return null;
    }

    @Override
    public void update(Mot item) {

    }

    @Override
    public void delete(Mot article) {

    }

    @Override
    public void delete(int id) {

    }
}
