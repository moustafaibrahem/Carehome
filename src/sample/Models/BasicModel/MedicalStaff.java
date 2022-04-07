package sample.Models.BasicModel;

import java.sql.SQLException;

public interface MedicalStaff {
    public Patient CheckPatient(long patientId) throws SQLException;
}
