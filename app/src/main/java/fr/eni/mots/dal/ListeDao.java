package fr.eni.mots.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import fr.eni.mots.model.Liste;

@Dao
public interface ListeDao {
    @Insert
    void insert(Liste item);

    @Query("SELECT * FROM Liste WHERE id = :id")
    LiveData<Liste> get(int id);

    @Query("SELECT * FROM Liste")
    LiveData<List<Liste>> get();

//    @Query("SELECT * FROM Liste WHERE id_niveau = :id")
//    LiveData<List<Liste>> get(int id);

    @Update
    void update(Liste item);

    @Delete
    void delete(Liste liste);
}
