package fr.eni.mots.repository.niveau;

import androidx.lifecycle.LiveData;

import java.util.List;

import fr.eni.mots.model.Niveau;

public interface INiveauRepository {

    void insert(Niveau item);

    Niveau get(int id);

    List<Niveau> get();

    void update(Niveau item);

    void delete(Niveau article);

    void delete(int id);
}
