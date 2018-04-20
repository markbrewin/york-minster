package uk.ac.lincoln.yorkminster;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "chests")
public class chestEntity {
    @NonNull
    @PrimaryKey
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

    @ColumnInfo(name = "cAlt")
    private double cAlt;

    public chestEntity(int id, String title, String info, double lat, double lon, double alt){
        this.cID = id;
        this.cTitle = title;
        this.cInfo = info;
        this.cLat = lat;
        this.cLong = lon;
        this.cAlt = alt;
    }

    public int getcID(){
        return cID;
    }

    public String getcTitle(){
        return cTitle;
    }

    public String getcInfo(){
        return cInfo;
    }

    public double getcLat(){
        return cLat;
    }

    public double getcLong(){
        return cLong;
    }

    public double getcAlt(){
        return cAlt;
    }
}
