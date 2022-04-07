package sample.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShiftsDB {

    static Connection connection = DataBaseConnection.getConnection();
    static String query = null;
    static PreparedStatement preparedStatement= null ;
    static ResultSet resultSet = null ;


    static public Shifts getShift(Staffshift staffshift) throws SQLException {
        Shifts shifts = new Shifts();
        query="SELECT * FROM `shifts` WHERE `shift_ID` ='"+staffshift.getShiftId()+"'";
        PreparedStatement preparedStatement1=connection.prepareStatement(query);
        resultSet=preparedStatement1.executeQuery();
        while (resultSet.next()){
            shifts = new Shifts(
                    resultSet.getLong(1),
                    resultSet.getTime(2),
                    resultSet.getTime(3)
            );
        }
        return shifts;
    }

    /*
     * this function return the list of shifts
     * */

    static public ObservableList<Shifts> getShiftsList() throws SQLException {
        ObservableList<Shifts>  shifts = FXCollections.observableArrayList();
        query = "SELECT * FROM shifts";
        connection = DataBaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            shifts.add(new Shifts(
                    resultSet.getLong("shift_ID"),
                    resultSet.getTime("start_time"),
                    resultSet.getTime("end_time")
            ));
        }
        return shifts;
    }

    /*
     * this function return the list of shifts id
     * */

    static public ObservableList getShiftsIdList() throws SQLException {
        ObservableList shiftsOption = FXCollections.observableArrayList();
        query = "SELECT * FROM shifts";
        connection = DataBaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            shiftsOption.add(String.valueOf(resultSet.getLong("shift_ID")));
        }

        return shiftsOption;
    }

}
