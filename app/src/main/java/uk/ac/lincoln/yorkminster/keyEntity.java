package uk.ac.lincoln.yorkminster;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
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

    @ColumnInfo(name = "kAlt")
    private double kAlt;

    public keyEntity(String kTitle, double kLat, double kLong, double kAlt){
        this.kTitle = kTitle;
        this.kLat = kLat;
        this.kLong = kLong;
        this.kAlt = kAlt;
    }

    public int getKID(){
        return kID;
    }

    public void setKID(int cID){
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

    public double getKAlt(){
        return kAlt;
    }

    public void setKAlt(Double kAlt){
        this.kAlt = kAlt;
    }
}
