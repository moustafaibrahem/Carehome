package sample.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientMedicinesDB {

    static Connection connection = DataBaseConnection.getConnection();
    static String query = null;
    static PreparedStatement preparedStatement= null ;
    static ResultSet resultSet = null ;


    /*
     * this function return the list of prescription that this patient taken and it active until now
     * */

    static public ObservableList<PatientMedicines> getPatientMedicinesList(long patientID) throws SQLException {
        ObservableList<PatientMedicines> patientMedicinesList = FXCollections.observableArrayList();
        query = "SELECT `prescription_ID`, `patient_id`, `doctor_id`, `medicine_id`, `reason`, `hourTime`, `doseAmount` FROM `patient_medicines` WHERE `patient_id`='" + patientID + "' AND active ='1';";
        connection = DataBaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        PatientMedicines patientMedicines;
        while (resultSet.next()) {
            patientMedicines = new PatientMedicines(
                    resultSet.getLong("prescription_ID"),
                    resultSet.getLong("patient_id"),
                    resultSet.getLong("doctor_id"),
                    resultSet.getLong("medicine_id"),
                    resultSet.getString("reason"),
                    resultSet.getLong("hourTime"),
                    resultSet.getLong("doseAmount")
            );
            patientMedicinesList.add(patientMedicines);
        }
        return patientMedicinesList;
    }


}