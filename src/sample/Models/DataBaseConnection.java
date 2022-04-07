package sample.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    public static Connection connection;

    /*
    * Here you find connected with Mysql Jar file -->>
    * ( fist thing you must add Mysql jar file to the External libraries you will find the jar file in folder -> DB)
    * that try to connected with your server
    * which his name is ->  "localhost"
    * and the DataBase name is -> "ablecarehome"
    * and the user name is -> "root"
    * and the password name is Empty
    * -------------------------------------
    * if there one of it don't right then you can't connect with database and on that program it will not work
    * */
    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/ablecarehome","root", "");
        } catch (ClassNotFoundException | SQLException ex) {

        }
        return connection;
    }

}
