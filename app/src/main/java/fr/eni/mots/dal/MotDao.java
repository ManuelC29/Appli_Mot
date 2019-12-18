package fr.eni.mots.dal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import fr.eni.mots.model.Liste;
import fr.eni.mots.model.Mot;

@Dao
public interface MotDao {
    @Insert
    void insert(Mot item);

    @Query("SELECT * FROM Mot WHERE id = :id")
    LiveData<Mot> get(int id);

    @Query("SELECT * FROM Mot")
    LiveData<List<Mot>> get();

    @Query("SELECT * " +
            "FROM Mot " +
            "INNER JOIN Liste " +
            "ON Mot.id_list = Liste.id " +
            "INNER JOIN Niveau ON id_niveau = Niveau.id " +
            "WHERE id_list = :idListe " +
            "AND id_niveau = :idNiveau")
    LiveData<List<Mot>> get(int idListe, int idNiveau);

    @Update
    void update(Mot item);

    @Delete
    void delete(Mot mot);
}
