package fr.eni.mots.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = Niveau.class,
        parentColumns = "id",
        childColumns = "id_niveau",
        onDelete = ForeignKey.NO_ACTION),
        indices = {@Index("id_niveau")}
)
public class Liste {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "id_niveau")
    private Integer idNiveau;
    @ColumnInfo(name = "libelle")
    private String libelle;


    public Liste(Integer id, String libelle, Integer idNiveau) {
        this.id = id;
        this.libelle = libelle;
        this.idNiveau = idNiveau;
    }

    public Integer getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(Integer idNiveau) {
        this.idNiveau = idNiveau;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


}
