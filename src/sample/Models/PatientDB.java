package sample.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Models.BasicModel.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDB {

    static Connection connection = DataBaseConnection.getConnection();
    static String query = null;
    static PreparedStatement preparedStatement= null ;
    static ResultSet resultSet = null ;

    /*
    * this function select patients information that active in system
    * */
    static public ObservableList<Patient> GetPatientsList() throws SQLException {

        ObservableList<Patient> patientsList= FXCollections.observableArrayList();
        query="SELECT `patient_ID`, `name`, `Phone`, `gender`, `isIsolted`, `admitted` , `bed_id` FROM `patients` where discharged is null ;";
        connection=DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        resultSet=preparedStatement.executeQuery();
        Patient patient;
        while (resultSet.next()){
            patient=new Patient(
                    resultSet.getLong("patient_ID"),
                    resultSet.getString("name"),
                    resultSet.getString("Phone"),
                    resultSet.getString("gender"),
                    resultSet.getLong("isIsolted"),
                    resultSet.getLong("bed_id"),
                    resultSet.getString("admitted")
            );
            patientsList.add(patient);
        }

        return patientsList;
    }


}
