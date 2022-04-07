package sample.Models.BasicModel;

import sample.Models.DataBaseConnection;
import sample.Models.LogsDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Employee extends Person {

    // prop
    public Connection connection = DataBaseConnection.getConnection();
    public String query = null;
    public PreparedStatement preparedStatement= null ;
    public ResultSet resultSet = null ;

    private String userName;
    private String password;
    private String type;

    // Constructor
    public Employee(long id, String name, String phone, String userName, String password, String type) {
        super(id,name,phone);
        this.userName = userName;
        this.password = password;
        this.type=type;
    }

    public Employee(String name, String phone, String userName, String password, String type)
    {
        super(name,phone);
        this.userName=userName;
        this.password=password;
        this.type=type;

    }
    public Employee(Employee employee)
    {
        super(employee.getId(),employee.getName(),employee.getPhone());
        this.userName= employee.getUserName();
        this.password= employee.getPassword();
        this.type= employee.getType();

    }


    public Employee(){
        super(0,"","");
        this.userName="";
        this.password="";
        this.type="";
    }

    // Getter
    public String getUserName()
    {
        return userName;
    }
    public String getPassword()
    {
        return password;
    }
    public String getType() {
        return type;
    }


    // Setter
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public void setType(String type) {
        this.type = type;
    }

    // Methods
    public void LogIn() throws SQLException {
        LogsDB.startSession(this.getId());
    }
    public void Logout() throws SQLException {
        LogsDB.endSession(this.getId());
    }
    public void ChangePassword() throws SQLException {
        query="UPDATE `staff` SET `password`= '"+this.getPassword()+"' WHERE `staff_ID`='"+this.getId()+"'";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return getUserName().equals(employee.getUserName()) && getPassword().equals(employee.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUserName(), getPassword());
    }
}
