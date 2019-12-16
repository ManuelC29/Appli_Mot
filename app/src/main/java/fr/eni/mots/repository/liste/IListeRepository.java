package fr.eni.mots.repository.niveau.liste;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.eni.mots.model.Liste;

public interface IListeRepository {
    void insert(Liste item);

    LiveData<Liste> get(int id);

    LiveData<List<Liste>> get();

    void update(Liste item);

    void delete(Liste article);

    void delete(int id);
}
