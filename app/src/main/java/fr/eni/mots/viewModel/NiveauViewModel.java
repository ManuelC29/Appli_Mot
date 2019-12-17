package fr.eni.mots.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.eni.mots.model.Niveau;
import fr.eni.mots.repository.niveau.INiveauRepository;
import fr.eni.mots.repository.niveau.NiveauBddRepository;

public class NiveauViewModel extends AndroidViewModel {

    INiveauRepository repository = null;
    LiveData<List<Niveau>> observateurListe = null;

    public NiveauViewModel(@NonNull Application application) {
        super(application);

        repository = new NiveauBddRepository(application);

        if(observateurListe == null)
        {
            observateurListe = repository.get();
        }
    }

    public LiveData<List<Niveau>> get()
    {
        return observateurListe;
    }

}
