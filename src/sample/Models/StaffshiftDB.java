package sample.Models;

import sample.Models.BasicModel.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaffshiftDB {

    static Connection connection = DataBaseConnection.getConnection();
    static String query = null;
    static PreparedStatement preparedStatement= null ;
    static ResultSet resultSet = null ;

    /*
     * this function return the information of the shift used by the shift of this staff to check if he allowed to get in this time or not
     * */
    static public Staffshift getShiftId(Employee employee) throws SQLException {
        Staffshift staffshift = new Staffshift();
        query="SELECT * FROM `staffshift` WHERE `staff_id` = '"+employee.getId()+"'";
        preparedStatement=connection.prepareStatement(query);
        resultSet=preparedStatement.executeQuery();
        while (resultSet.next()){
            staffshift = new Staffshift(
                    resultSet.getLong(1),
                    resultSet.getLong(2),
                    resultSet.getLong(3)
            );
        }
        return staffshift;
    }


}
