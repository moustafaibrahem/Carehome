package sample.Models.BasicModel;

import sample.Models.DataBaseConnection;
import sample.Models.PatientMedicines;
import java.sql.SQLException;

public class Doctor extends Employee implements MedicalStaff{


    // Constructor
    public Doctor(long id, String name, String phone, String userName, String password, String type) {
        super(id, name, phone, userName, password,type);
    }
    public Doctor(String name, String phone, String userName, String password, String type) {
        super(name, phone, userName, password,type);
    }
    public Doctor(Employee employee) {
        super(employee);
    }

    public Doctor() {
        super(0, "", "", "", "","");
    }


    // Methods

    /*
     * this function used to add prescription to this patient
     * */

    public void AddPrescription(PatientMedicines prescription) throws SQLException {
        query = "INSERT INTO `patient_medicines`(`patient_id`, `doctor_id`, `medicine_id`, `reason`, `hourTime`,`doseAmount`) VALUES ('"+prescription.getPatientId()+"','"+prescription.getDoctorId()+"','"+prescription.getMedicineId()+"','"+prescription.getReason()+"','"+prescription.getHourTime()+"','"+prescription.getDoseAmount()+"')";
        connection = DataBaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }

    /*
     * this function used to update the prescription like reason or every hour ? and the amount of this medicine
     * */

    public void EditPrescription(PatientMedicines prescription) throws SQLException {
        query="UPDATE `patient_medicines` SET `reason`='"+prescription.getReason()+"',`hourTime`='"+prescription.getHourTime()+"',`doseAmount`='"+prescription.getDoseAmount()+"' WHERE prescription_ID = '"+prescription.getPrescriptionId()+"';";
        connection=DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();

    }

    /*
     * this function make the prescription  deactivated  and this patient doesn't take it but still in database
     * */

    public void DeletePrescription(PatientMedicines prescription) throws SQLException {
        query="UPDATE `patient_medicines` SET `active`='0' WHERE prescription_ID = '"+prescription.getPrescriptionId()+"';";
        connection=DataBaseConnection.getConnection();
        preparedStatement=connection.prepareStatement(query);
        preparedStatement.execute();


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
