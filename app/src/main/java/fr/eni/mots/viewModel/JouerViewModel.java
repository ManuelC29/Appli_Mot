package fr.eni.mots.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import fr.eni.mots.model.Mot;
import fr.eni.mots.repository.mot.IMotRepository;
import fr.eni.mots.repository.mot.MotBddRepository;


public class JouerViewModel extends AndroidViewModel {
    IMotRepository repository = null;
    LiveData<List<Mot>> observateurListe = null;

    public JouerViewModel(@NonNull Application application) {
        super(application);

        repository = new MotBddRepository(application);

        if(observateurListe == null)
        {
            observateurListe = repository.get();
        }
    }

    public MutableLiveData<List<Mot>> getMotsListNivObserver(int idList)
    {
        return repository.getMotsList(idList);
    }

    public MutableLiveData<Mot> updateMot(Mot mot){
        repository.update(mot);
        return null;
    }


}
