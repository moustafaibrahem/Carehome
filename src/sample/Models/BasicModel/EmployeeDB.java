package sample.Models.BasicModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Models.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class EmployeeDB {

    static Connection connection = DataBaseConnection.getConnection();
    static String query = null;
    static PreparedStatement preparedStatement= null ;
    static ResultSet resultSet = null ;

    /*
     * this function return the list staff and his shift info
     * */

    static public ObservableList<Employee> GetEmployeeList() throws SQLException {
        ObservableList<Employee> EmployeeList= FXCollections.observableArrayList();
        query="SELECT s.staff_ID, s.name, s.phone, s.username, s.password, s.type_id  FROM `staff`s  WHERE  endeffectiveDate is NULL;";
        connection=DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        resultSet= preparedStatement.executeQuery();
        Employee employee;
        while (resultSet.next()) {
            employee = new Employee(
                    resultSet.getLong("staff_ID"),
                    resultSet.getString("name"),
                    resultSet.getString("phone"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("type_id")
            );
            EmployeeList.add(employee);
        }
        return EmployeeList;
    }

    /*
     * this function return staff information under there username and password
     * when it doesn't  found then the object will return the id by 0 and there we check for it
     * */

    static public Employee getEmployee(Employee employee) throws SQLException {
        Employee resultEmployee = new Employee();
        query="select * from staff where username ='"+employee.getUserName()+"' and password ='"+employee.getPassword()+"' And endeffectiveDate is NULL;";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            resultEmployee = new Employee(
                    resultSet.getLong("staff_ID"),
                    resultSet.getString("name"),
                    resultSet.getString("phone"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("type_id")
            );
        }
        return resultEmployee;
    }

    static public Employee getLoginEmployee(Employee employee) throws SQLException {
        Employee resultEmployee =EmployeeDB.getEmployee(employee);
        if (resultEmployee.getType().equals("Manager")||resultEmployee.getType().equals("Doctor")) { return resultEmployee; }
        else if(resultEmployee.getType().equals("Nurse")){

            Staffshift staffshift=StaffshiftDB.getShiftId(resultEmployee);
            //check his shift
            Shifts shifts = ShiftsDB.getShift(staffshift);
            Time startTime = shifts.getStartTime();
            Time endTime = shifts.getEndTime();
            DateFormat format = new SimpleDateFormat("HH:mm");
            String strStartTime=format.format(startTime.getTime());
            String strEndTime=format.format(endTime.getTime());

            LocalTime now = LocalTime.now();
            LocalTime limit1 = LocalTime.parse(strStartTime);
            LocalTime limit2 = LocalTime.parse(strEndTime);
            Boolean isLate = now.isAfter(limit2);
            Boolean isEarly=now.isBefore(limit1);

            if (!isLate && !isEarly){ return resultEmployee; }
            //that is not his shift
            else {
                resultEmployee = new Employee();
                return resultEmployee;
            }
        }
        else{
            resultEmployee = new Employee();
            return resultEmployee;
        }
    }


}
