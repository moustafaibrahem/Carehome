package sample.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministrationsDB {

    static Connection connection = DataBaseConnection.getConnection();
    static String query = null;
    static PreparedStatement preparedStatement= null ;
    static ResultSet resultSet = null ;

    static public int getRowNumber(long prescriptionId) throws SQLException {
        query="SELECT count(`prescription_id`) as 'count' FROM `administrations` WHERE prescription_id = '"+prescriptionId+"' ;";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            return resultSet.getInt(1);
        }
        return 0;
    }

    static public Administrations getAdministrations(long prescriptionId) throws SQLException {
        Administrations administrations = new Administrations();
        query="select id,prescription_id,nurse_id,TIMESTAMPDIFF(HOUR, ad.created_at, SYSDATE()) as 'def' from administrations ad where ad.prescription_id='"+prescriptionId+"';";
        connection= DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        resultSet= preparedStatement.executeQuery();
        while (resultSet.next()) {
            administrations = new Administrations(
                    resultSet.getLong("id"),
                    resultSet.getLong("prescription_id"),
                    resultSet.getLong("nurse_id"),
                    resultSet.getLong("def")
            );
        }
        return administrations;
    }

    static public void addAdministrations(long prescriptionId, long nurseId) throws SQLException {
        query = "INSERT INTO `administrations`(`prescription_id`, `nurse_id`) VALUES ('" +prescriptionId+ "','"+nurseId+"')";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }


}
