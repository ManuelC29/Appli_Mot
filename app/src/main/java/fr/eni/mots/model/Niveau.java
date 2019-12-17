package fr.eni.mots.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Niveau  implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "libelle")
    private String libelle;

    public Niveau(Integer id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    protected Niveau(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        libelle = in.readString();
    }

    public static final Creator<Niveau> CREATOR = new Creator<Niveau>() {
        @Override
        public Niveau createFromParcel(Parcel in) {
            return new Niveau(in);
        }

        @Override
        public Niveau[] newArray(int size) {
            return new Niveau[size];
        }
    };

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
    public String toString() {
        return "Niveaux{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
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
        dest.writeString(libelle);
    }
}
