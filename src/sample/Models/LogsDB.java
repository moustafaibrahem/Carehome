package sample.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogsDB {

    static Connection connection = DataBaseConnection.getConnection();
    static String query = null;
    static PreparedStatement preparedStatement= null ;
    static ResultSet resultSet = null ;

    /*
    * this function insert into logs table when any one get into the system
    * */
    static public void  startSession(long employeeId) throws SQLException {
        query="INSERT INTO `logs`(`staff_id`) VALUES ('"+employeeId+"')";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();
    }


    /*
    * this function record when he logout the system and updated the row
    * then we have what's time he gets in and out of the system
    * */
    static  public void endSession(long employeeId) throws SQLException {
        long log_id;
        connection= DataBaseConnection.getConnection();
        query="SELECT `id` FROM `logs` WHERE `staff_id`= '"+employeeId+"' AND endSessionDate IS NULL ";
        preparedStatement=connection.prepareStatement(query);
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            log_id=resultSet.getLong("id");
            query="UPDATE `logs` SET `endSessionDate`= SYSDATE() WHERE `id`='"+log_id+"'";
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.execute();
        }
    }

}
