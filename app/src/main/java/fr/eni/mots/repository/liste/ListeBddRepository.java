package fr.eni.mots.repository.liste;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.eni.mots.model.Liste;

public class ListeBddRepository implements fr.eni.mots.repository.niveau.liste.IListeRepository {
    @Override
    public void insert(Liste item) {

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
