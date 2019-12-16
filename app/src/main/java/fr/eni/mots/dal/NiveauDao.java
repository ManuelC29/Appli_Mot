package fr.eni.mots.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import fr.eni.mots.model.Liste;
import fr.eni.mots.model.Niveau;

@Dao
public interface NiveauDao {
    @Insert
    void insert(Niveau item);

    @Query("SELECT * FROM Niveau WHERE id = :id")
    LiveData<Liste> get(int id);

    @Query("SELECT * FROM Niveau")
    LiveData<List<Niveau>> get();

    @Update
    void update(Niveau item);

    @Delete
    void delete(Niveau niveau);
}
