package fr.eni.mots.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.eni.mots.model.Liste;
import fr.eni.mots.repository.liste.IListeRepository;
import fr.eni.mots.repository.liste.ListeBddRepository;

public class ListeViewModel extends AndroidViewModel {

    IListeRepository repository = null;
    LiveData<List<Liste>> observateurListe = null;

    public ListeViewModel(@NonNull Application application) {
        super(application);

        repository = new ListeBddRepository(application);

        if(observateurListe == null)
        {
            observateurListe = repository.get();
        }
    }

    public LiveData<List<Liste>> getListByIdNivObserver(int id)
    {

       return repository.getListByIdNiv(id);
      //  return observateurListe;
    }


}
