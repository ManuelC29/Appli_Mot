package fr.eni.mots.repository.mot;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.eni.mots.model.Mot;

public class MotBddRepository implements IMotRepository{
    @Override
    public void insert(Mot item) {

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
