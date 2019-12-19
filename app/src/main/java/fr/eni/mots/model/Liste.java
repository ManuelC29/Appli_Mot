package fr.eni.mots.model;


import android.os.Parcel;
import android.os.Parcelable;

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
public class Liste implements Parcelable {
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

    protected Liste(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            idNiveau = null;
        } else {
            idNiveau = in.readInt();
        }
        libelle = in.readString();
    }

    public static final Creator<Liste> CREATOR = new Creator<Liste>() {
        @Override
        public Liste createFromParcel(Parcel in) {
            return new Liste(in);
        }

        @Override
        public Liste[] newArray(int size) {
            return new Liste[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        if (idNiveau == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(idNiveau);
        }
        dest.writeString(libelle);
    }

    @Override
    public String toString() {
        return "Liste{" +
                "id=" + id +
                ", idNiveau=" + idNiveau +
                ", libelle='" + libelle + '\'' +
                '}';
    }
}
