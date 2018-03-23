package uk.ac.lincoln.yorkminster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Marcus on 22/03/2018.
 */

public class dbKey {
    private static Connection con;
    private static boolean hasData = false;

    public ResultSet displayInfo() throws ClassNotFoundException, SQLException{
        if(con == null){
            getConnection();
        }
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT kTitle, kLat, kLong, kAlt FROM key");
        return res;
    }
    private void getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:dbKey.db");
        initialise();
    }
    private void initialise() throws SQLException{
        if(!hasData){
            hasData = true;
            Statement state = con.createStatement();
            ResultSet res = state.executeQuery("SELECT info FROM sqlite_master WHERE type='table' AND name ='key'");
            if(!res.next()){
                System.out.print("Building table");

                Statement state2 = con.createStatement();
                state2.execute("CREATE TABLE key(id integer,"
                        + "kTitle varchar(60)," + "kLat varchar(255)," + "kLong varchar(255)," + "kAlt varchar(255),"
                        + "primary key(id);");

                //NAVE
                PreparedStatement prep = con.prepareStatement("INSERT INTO key values(?, ?, ?, ?, ?);");
                prep.setString(2, "Nave Key");
                prep.setString(3, "Latitude");
                prep.setString(4, "Longitude");
                prep.setString(5, "Altitude");
                prep.execute();

                //THE STRIKING CLOCK
                PreparedStatement prep2 = con.prepareStatement("INSERT INTO key values(?, ?, ?, ?, ?);");
                prep2.setString(2, "The Striking Clock Key");
                prep2.setString(3, "Latitude");
                prep2.setString(4, "Longitude");
                prep2.setString(5, "Altitude");
                prep2.execute();

                //THE DRAGON
                PreparedStatement prep3 = con.prepareStatement("INSERT INTO key values(?, ?, ?, ?, ?);");
                prep3.setString(2, "The Dragon Key");
                prep3.setString(3, "Latitude");
                prep3.setString(4, "Longitude");
                prep3.setString(5, "Altitude");
                prep3.execute();

                //THE FIDDLER
                PreparedStatement prep4 = con.prepareStatement("INSERT INTO key values(?, ?, ?, ?, ?);");
                prep4.setString(2, "The Fiddler Key");
                prep4.setString(3, "Latitude");
                prep4.setString(4, "Longitude");
                prep4.setString(5, "Altitude");
                prep4.execute();

                //SOUTH TRANSEPT AND ROSE WINDOW
                PreparedStatement prep5 = con.prepareStatement("INSERT INTO key values(?, ?, ?, ?, ?);");
                prep5.setString(2, "South Transept and Rose Window Key");
                prep5.setString(3, "Latitude");
                prep5.setString(4, "Longitude");
                prep5.setString(5, "Altitude");
                prep5.execute();

                //NORTH QUIRE AISLE
                PreparedStatement prep6 = con.prepareStatement("INSERT INTO key values(?, ?, ?, ?, ?);");
                prep6.setString(2, "North Quire Aisle Key");
                prep6.setString(3, "Latitude");
                prep6.setString(4, "Longitude");
                prep6.setString(5, "Altitude");
                prep6.execute();

                //CATHEDRA
                PreparedStatement prep7 = con.prepareStatement("INSERT INTO key values(?, ?, ?, ?, ?);");
                prep7.setString(2, "Cathedra Key");
                prep7.setString(3, "Latitude");
                prep7.setString(4, "Longitude");
                prep7.setString(5, "Altitude");
                prep7.execute();

                //CENTRAL TOWER
                PreparedStatement prep8 = con.prepareStatement("INSERT INTO key values(?, ?, ?, ?, ?);");
                prep8.setString(2, "Central Tower Key");
                prep8.setString(3, "Latitude");
                prep8.setString(4, "Longitude");
                prep8.setString(5, "Altitude");
                prep8.execute();


                //THE CHAPTER HOUSE
                PreparedStatement prep9 = con.prepareStatement("INSERT INTO key values(?, ?, ?, ?, ?);");
                prep9.setString(2, "The Chapter House Key");
                prep9.setString(3, "Latitude");
                prep9.setString(4, "Longitude");
                prep9.setString(5, "Altitude");
                prep9.execute();

                //FIVE SISTERS WINDOW
                PreparedStatement prep10 = con.prepareStatement("INSERT INTO key values(?, ?, ?, ?, ?);");
                prep10.setString(2, "Five Sisters Window Key");
                prep10.setString(3, "Latitude");
                prep10.setString(4, "Longitude");
                prep10.setString(5, "Altitude");
                prep10.execute();
            }
        }
    }
}
