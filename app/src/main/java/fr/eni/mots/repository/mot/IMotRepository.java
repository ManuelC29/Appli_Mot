package fr.eni.mots.repository.mot;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.eni.mots.model.Mot;
import fr.eni.mots.model.Niveau;

public interface IMotRepository {

    void insert(Mot item);

    LiveData<Mot> get(int id);

    LiveData<List<Mot>> get();

    void update(Mot item);

    void delete(Mot article);

    void delete(int id);
}
