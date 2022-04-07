package sample.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WardsDB {

    static Connection connection = DataBaseConnection.getConnection();
    static String query = null;
    static PreparedStatement preparedStatement= null ;
    static ResultSet resultSet = null ;

    /*
     * this function return the list of Wards Id
     * */
    static public ObservableList getWardsIdList() throws SQLException {
        ObservableList wardOption = FXCollections.observableArrayList();
        query="SELECT * FROM `wards`";
        connection = DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        resultSet= preparedStatement.executeQuery();
        while (resultSet.next())
        {
            wardOption.add(String.valueOf(resultSet.getLong("ward_ID")));
        }
        return wardOption;

    }
}
