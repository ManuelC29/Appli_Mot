package fr.eni.mots.dal;

import androidx.lifecycle.LiveData;
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
    LiveData<Liste> get(int id);

    @Query("SELECT * FROM Mot")
    LiveData<List<Liste>> get();

    @Update
    void update(Mot item);

    @Delete
    void delete(Mot mot);
}
