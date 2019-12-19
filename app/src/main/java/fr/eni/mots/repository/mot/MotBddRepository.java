package fr.eni.mots.repository.mot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    MutableLiveData<List<Mot>> motListObserver = new MutableLiveData<>();

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
        return dao.get(id);
    }

    @Override
    public LiveData<List<Mot>> get() {
        return dao.get();
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public MutableLiveData<List<Mot>> getMotsList(int idListe) {
        new AsyncTask<Integer, Void,List<Mot>>(){

            @Override
            protected List<Mot> doInBackground(Integer... integers) {
                return dao.getMotsList(integers[0]);
            }

            @Override
            protected void onPostExecute(List<Mot> mots) {
                super.onPostExecute(mots);
                motListObserver.setValue(mots);
            }
        }.execute(idListe);
        return motListObserver;
    }


    @SuppressLint("StaticFieldLeak")
    @Override
    public void update(Mot item) {
        new AsyncTask<Mot,Void,Void>(){

            @Override
            protected Void doInBackground(Mot... mots) {
                dao.update(item);
                return null;
            }
        }.execute();

    }

    @Override
    public void delete(Mot article) {

    }

    @Override
    public void delete(int id) {

    }
}
