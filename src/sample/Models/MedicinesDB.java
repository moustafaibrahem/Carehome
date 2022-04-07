package sample.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicinesDB {

    static Connection connection = DataBaseConnection.getConnection();
    static String query = null;
    static PreparedStatement preparedStatement= null ;
    static ResultSet resultSet = null ;

    /*
     * this function return the list of Medicines
     * */

    static public ObservableList<Medicines> getMedicinesList() throws SQLException {
        ObservableList<Medicines>  medicines = FXCollections.observableArrayList();
        query="SELECT * FROM `medicines`";
        connection = DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        resultSet= preparedStatement.executeQuery();
        while (resultSet.next())
        {
            medicines.add(new Medicines(
                    resultSet.getLong("medicine_ID"),
                    resultSet.getString("name"),
                    resultSet.getString("Unit"),
                    resultSet.getLong("Amount")
            ));
        }
        return medicines;
    }

    /*
     * this function return the list of Medicines Name
     * */

    static public ObservableList getMedicinesNameList() throws SQLException {
        ObservableList medicineOption= FXCollections.observableArrayList();
        query="SELECT * FROM `medicines`";
        connection = DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        resultSet= preparedStatement.executeQuery();
        while (resultSet.next())
        {
            medicineOption.add(String.valueOf(resultSet.getString("name")));
        }
        return medicineOption;
    }

}
