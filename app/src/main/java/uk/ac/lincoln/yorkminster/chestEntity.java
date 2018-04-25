package uk.ac.lincoln.yorkminster;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

@Entity(tableName = "chests")
public class chestEntity {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cID")
    private int cID;

    @ColumnInfo(name = "cTitle")
    private String cTitle;

    @ColumnInfo(name = "cInfo")
    private String cInfo;

    @ColumnInfo(name = "cLat")
    private double cLat;

    @ColumnInfo(name = "cLong")
    private double cLong;

    @Ignore
    public chestEntity(int cID, String cTitle, String cInfo, double cLat, double cLong){
        this.cID = cID;
        this.cTitle = cTitle;
        this.cInfo = cInfo;
        this.cLat = cLat;
        this.cLong = cLong;
    }

    public chestEntity(String cTitle, String cInfo, double cLat, double cLong){
        this.cTitle = cTitle;
        this.cInfo = cInfo;
        this.cLat = cLat;
        this.cLong = cLong;
    }

    public int getCID(){
        return cID;
    }

    public void setCID(int cID){
        this.cID = cID;
    }

    public String getCTitle(){
        return cTitle;
    }

    public void setCTitle(String cTitle){
        this.cTitle = cTitle;
    }

    public String getCInfo(){
        return cInfo;
    }

    public void setCInfo(String cInfo){
        this.cInfo = cInfo;
    }

    public double getCLat(){
        return cLat;
    }

    public void setCLat(Double cLat){
        this.cLat = cLat;
    }

    public double getCLong(){
        return cLong;
    }

    public void setCLong(Double cLong){
        this.cLong = cLong;
    }
}
