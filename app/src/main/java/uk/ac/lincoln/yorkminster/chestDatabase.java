package uk.ac.lincoln.yorkminster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Marcus on 20/03/2018.
 */

public class chestDatabase {
    private static Connection con;
    private static boolean hasData = false;

    public ResultSet displayInfo() throws ClassNotFoundException, SQLException{
        if(con == null){
            getConnection();
        }
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT cTitle, cInfo FROM chest");
        return res;
    }
    private void getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:ChestContents.db");
        initialise();
    }
    private void initialise() throws SQLException{
        if(!hasData){
            hasData = true;
            Statement state = con.createStatement();
            ResultSet res = state.executeQuery("SELECT info FROM sqlite_master WHERE type='table AND name ='chest");
            if(!res.next()){
                System.out.print("Bulding table");

                Statement state2 = con.createStatement();
                state2.execute("CREATE TABLE chest(id integer,"
                        + "cTitle varchar(60), " + "cInfo varchar(255),"
                        + "primary key(id);");

                PreparedStatement prep = con.prepareStatement("INSERT INTO chest values(?, ?, ?);");
                prep.setString(2, "Title");
                prep.setString(3, "Info");

                PreparedStatement prep2 = con.prepareStatement("INSERT INTO chest values(?, ?, ?);");
                prep.setString(2, "Title");
                prep.setString(3, "Info");
            }
        }
    }
}
