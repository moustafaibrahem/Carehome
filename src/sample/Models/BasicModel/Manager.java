package sample.Models.BasicModel;

import sample.Models.DataBaseConnection;
import sample.Models.RoomsDB;
import java.sql.SQLException;

public class Manager extends Employee{


    public Manager(long id, String name, String phone, String userName, String password, String type) {
        super(id, name, phone, userName, password, type);
    }
    public Manager() {
        super(0, "", "", "", "", "");
    }

    // Methods

    /*
     * this function used to add a Doctor to the system
     * */

    public void AddDoctor(Doctor doctor) throws SQLException {
        query="INSERT INTO `staff` (`name`, `phone`, `username`, `password`, `type_id`) VALUES ('"+doctor.getName()+"', '"+doctor.getPhone()+"', '"+doctor.getUserName()+"', '"+doctor.getPassword()+"', 'Doctor');";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();
    }

    /*
     * this function used to update the Doctor information
     * */

    public void EditDoctor(Doctor doctor) throws SQLException {
        query="UPDATE `staff` SET `name`='"+doctor.getName()+"',`phone`='"+doctor.getPhone()+"',`username`='"+doctor.getUserName()+"',`password`='"+doctor.getPassword()+"' WHERE `staff_ID`='"+doctor.getId()+"'";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }

    /*
     * this function used to update the Doctor endeffectiveDate by the sysdate when the manager click delete
     * */

    public void DeleteDoctor(Doctor doctor) throws SQLException {
        query="UPDATE `staff` SET `endeffectiveDate`= SYSDATE() WHERE `staff_ID`='"+doctor.getId()+"'";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();
    }

    /*
     * this function used to add a Nurse to the system
     * */

    public void AddNurse(Nurse nurse) throws SQLException {
        query="INSERT INTO `staff` (`name`, `phone`, `username`, `password`,`type_id`) VALUES ('"+nurse.getName()+"', '"+nurse.getPhone()+"', '"+nurse.getUserName()+"', '"+nurse.getPassword()+"', 'Nurse');";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();


        query="select `staff_ID`  from staff where username ='"+nurse.getUserName()+"' and password ='"+nurse.getPassword()+"' And endeffectiveDate is NULL;";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            nurse.setId(resultSet.getLong("staff_ID"));
        }
        query="INSERT INTO `staffshift`(`staff_id`, `shift_id`) VALUES ('"+nurse.getId()+"','"+nurse.getShiftId()+"')";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();

    }

    /*
     * this function used to a update the Nurse information
     * */

    public void EditNurse(Nurse nurse) throws SQLException {
        query="UPDATE `staff` SET `name`='"+nurse.getName()+"',`phone`='"+nurse.getPhone()+"',`username`='"+nurse.getUserName()+"',`password`='"+nurse.getPassword()+"' WHERE `staff_ID`='"+nurse.getId()+"'";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();

        query="UPDATE `staffshift` SET `shift_id`='"+nurse.getShiftId()+"' WHERE `staff_id`='"+nurse.getId()+"'";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }

    /*
     * this function used to update the Nurse endeffectiveDate by the sysdate when the manager click delete
     * */

    public void DeleteNurse(Nurse nurse) throws SQLException {
        query="UPDATE `staff` SET `endeffectiveDate`= SYSDATE() WHERE `staff_ID`='"+nurse.getId()+"'";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();
    }

    /*
     * this function used to add a patient to the system
     * */

    public void AddPatient(Patient patient) throws SQLException {
        query="INSERT INTO `patients`(`name`, `Phone`, `gender`, `isIsolted`, `admitted`,`bed_id`) VALUES ('"+patient.getName()+"','"+patient.getPhone()+"','"+patient.getGender()+"','"+patient.getIsIsolted()+"', '"+patient.getAdmitted()+"','"+patient.getBedId()+"')";
        connection=DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();
    }

    /*
     * this function used to a Update patient information
     * */

    public void EditPatient(Patient patient) throws SQLException {
        query="UPDATE `patients` SET `name`='"+patient.getName()+"',`phone`='"+patient.getPhone()+"' WHERE `patient_ID`='"+patient.getId()+"'";
        connection=DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();
    }

    /*
     * this function used to a Update patient information and put his discharged date by the time the manager discharged him
     * */

    public void DeletePatient(Patient patient) throws SQLException {
        query="UPDATE `patients` SET `discharged`= SYSDATE() WHERE `patient_ID`='"+patient.getId()+"'";
        connection=DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();

        long previousRoomId = RoomsDB.getRoomId(patient.getBedId());
        if (RoomsDB.getUsedBedNumberInRoom(previousRoomId)==0){
            RoomsDB.updateRoomType(String.valueOf(previousRoomId));
        }

    }

}
