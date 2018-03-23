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

public class dbUser {
    private static Connection con;
    private static boolean hasData = false;

    public ResultSet displayInfo() throws ClassNotFoundException, SQLException{
        if(con == null){
            getConnection();
        }
        Statement state = con.createStatement();
        ResultSet res = state.executeQuery("SELECT fName, lName, FROM user");
        return res;
    }
    private void getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("org.sqlite.JDBC");
        con = DriverManager.getConnection("jdbc:sqlite:dbUser.db");
        initialise();
    }
    private void initialise() throws SQLException{
        if(!hasData){
            hasData = true;
            Statement state = con.createStatement();
            ResultSet res = state.executeQuery("SELECT info FROM sqlite_master WHERE type='table' AND name ='user'");
            if(!res.next()){
                System.out.print("Building table");

                Statement state2 = con.createStatement();
                state2.execute("CREATE TABLE user(id integer,"
                        + "fName varchar(60)," + "lName varchar(255)," + "primary key(id);");

                //NAVE
                PreparedStatement prep = con.prepareStatement("INSERT INTO key values(?, ?, ?);");
                prep.setString(2, null);
                prep.setString(3, null);
                prep.execute();
            }
        }
    }
    public void addUser (String firstName, String lastName) throws ClassNotFoundException, SQLException {
        if(con == null){
            getConnection();
        }
        PreparedStatement prep = con.prepareStatement("INSERT INTO user values(?, ?, ?);");
        prep.setString(2, firstName);
        prep.setString(3, lastName);
        prep.execute();
    }
}
