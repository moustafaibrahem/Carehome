package sample.Models.BasicModel;

import sample.Models.*;
import java.sql.SQLException;

public class Nurse extends Employee implements MedicalStaff{

    // prop
    private long shiftId;

    // Constructor
    public Nurse(long id, String name, String phone, String userName, String password, String type, long shiftId) {
        super(id, name, phone, userName, password, type);
        this.shiftId=shiftId;
    }

    public Nurse(String name, String phone, String userName, String password, String type, long shiftId) {
        super(name, phone, userName, password, type);
        this.shiftId=shiftId;
    }

    public Nurse(Employee employee, long shiftId) {
        super(employee);
        this.shiftId=shiftId;
    }

    public Nurse(Employee employee) {
        super(employee);
        this.shiftId=0;
    }

    public Nurse() {
        super(0, "", "", "","","");
        this.shiftId=0;
    }

    // Getter
    public long getShiftId()
    {
        return shiftId;
    }

    // Setter
    public void setShiftId(long shiftId) {
        this.shiftId = shiftId;
    }


    // Methods

    /*
    * insert a row with the time of the last taken prescription and who has done it
    * */
    public void GivePrescription(PatientMedicines prescription) throws SQLException {
        query = "INSERT INTO `administrations`(`prescription_id`, `nurse_id`) VALUES ('" +prescription.getPrescriptionId()+ "','"+this.getId()+"')";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }

    /*
     * this function Update patient Bed when the nurse move him form bed to anther bed
     * then recorde the previous bed and the new bed
     * then check in the room type to make it free or not
     * */
    public void ChangeBedFromTo(Patient patient, String toBedName, String selectedRoom) throws SQLException {

        Rooms rooms= RoomsDB.getRoom(selectedRoom);

        query="UPDATE `patients` SET `bed_id`= '"+toBedName+"' WHERE `patient_ID`='"+patient.getId()+"'";
        connection=DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();

        query="INSERT INTO `changes_beds`(`patient_id`, `fromBed_id`, `toBed_id`) VALUES ('"+patient.getId()+"','"+patient.getBedId()+"','"+toBedName+"')";
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();

        if (rooms.getTypeId().equals("free")){
            RoomsDB.updateRoomType(patient.getGender(), String.valueOf(rooms.getRoomId()));
        }
        long previousRoomId = RoomsDB.getRoomId(patient.getBedId());
        if (RoomsDB.getUsedBedNumberInRoom(previousRoomId)==0){
            RoomsDB.updateRoomType(String.valueOf(previousRoomId));
        }

    }

    /*
     * this function select patient information by id
     * */

    @Override
    public Patient CheckPatient(long patientId) throws SQLException {
        Patient patient = new Patient();
        query = "SELECT `patient_ID`, `name`, `Phone`, `gender`, `isIsolted`, `admitted` , `bed_id` FROM `patients` where `patient_ID` = '"+patientId+"' AND  discharged is null ;";
        connection = DataBaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            patient = new Patient(
                    resultSet.getLong("patient_ID"),
                    resultSet.getString("name"),
                    resultSet.getString("Phone"),
                    resultSet.getString("gender"),
                    resultSet.getLong("isIsolted"),
                    resultSet.getLong("bed_id"),
                    resultSet.getString("admitted")
            );
        }
        return patient;
    }

}
