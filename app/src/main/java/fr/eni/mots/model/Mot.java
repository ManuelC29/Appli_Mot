package fr.eni.mots.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = Liste.class,
        parentColumns = "id",
        childColumns = "id_list",
        onDelete = ForeignKey.CASCADE),
        indices = {@Index("id_list")}
)
public class Mot {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "id_list")
    private Integer idList;
    @ColumnInfo(name = "mot_correct")
    private String motCorrect;
    @ColumnInfo(name = "image")
    private String image;
    @ColumnInfo(name = "mot_propose")
    private String motPropose;
    @ColumnInfo(name = "etat")
    private Integer etat;

    public Mot(Integer id, String motCorrect, String image, String motPropose, Integer etat, Integer idList) {
        this.id = id;
        this.motCorrect = motCorrect;
        this.image = image;
        this.motPropose = motPropose;
        this.etat = etat;
        this.idList = idList;
    }

    public Integer getIdList() {
        return idList;
    }

    public void setIdList(Integer idList) {
        this.idList = idList;
    }

    public Integer getId() {
        return id;
    }

    public String getMotCorrect() {
        return motCorrect;
    }

    public String getImage() {
        return image;
    }

    public String getMotPropose() {
        return motPropose;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMotCorrect(String motCorrect) {
        this.motCorrect = motCorrect;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setMotPropose(String motPropose) {
        this.motPropose = motPropose;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Mot{" +
                "id=" + id +
                ", motCorrect='" + motCorrect + '\'' +
                ", image='" + image + '\'' +
                ", motPropose='" + motPropose + '\'' +
                ", etat=" + etat +
                ", idList=" + idList +
                '}';
    }
}
