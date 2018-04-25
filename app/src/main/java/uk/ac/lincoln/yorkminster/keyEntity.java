package uk.ac.lincoln.yorkminster;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "keys")
public class keyEntity {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "kID")
    private int kID;

    @ColumnInfo(name = "kTitle")
    private String kTitle;

    @ColumnInfo(name = "kLat")
    private double kLat;

    @ColumnInfo(name = "kLong")
    private double kLong;

    @Ignore
    public keyEntity(int kID, String kTitle, double kLat, double kLong){
        this.kID = kID;
        this.kTitle = kTitle;
        this.kLat = kLat;
        this.kLong = kLong;
    }

    public keyEntity(String kTitle, double kLat, double kLong){
        this.kTitle = kTitle;
        this.kLat = kLat;
        this.kLong = kLong;
    }

    public int getKID(){
        return kID;
    }

    public void setKID(int kID){
        this.kID = kID;
    }

    public String getKTitle(){
        return kTitle;
    }

    public void setKTitle(String kTitle){
        this.kTitle = kTitle;
    }

    public double getKLat(){
        return kLat;
    }

    public void setKLat(Double kLat){
        this.kLat = kLat;
    }

    public double getKLong(){
        return kLong;
    }

    public void setKLong(Double kLong){
        this.kLong = kLong;
    }
}
