package sample.Models.BasicModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Models.PatientMedicines;
import sample.Models.PatientMedicinesDB;
import java.sql.SQLException;

public class Patient extends Person{

    // prop
    private String gender;
    private long isIsolted;
    private long bedId;
    private String admitted;
    private ObservableList<PatientMedicines> patientMedicinesList;

    // constructor
    public Patient(long id, String name, String phone, String gender, long isIsolted, long bedId, String admitted)
    {
        super(id,name,phone);
        this.gender=gender;
        this.isIsolted=isIsolted;
        this.bedId = bedId;
        this.admitted = admitted;
    }

    public Patient(String name, String phone, String gender, long isIsolted, long bedId, String admitted) {
        super(name,phone);
        this.gender = gender;
        this.isIsolted = isIsolted;
        this.bedId = bedId;
        this.admitted = admitted;
    }

    public Patient(){
        super(0,"","");
        this.gender = "";
        this.isIsolted = 0;
        this.admitted = "";
        this.bedId = 0;
    }

    // Getter
    public String getGender()
    {
        return gender;
    }
    public long getIsIsolted()
    {
        return isIsolted;
    }
    public long getBedId() {
        return bedId;
    }
    public String getAdmitted() {
        return admitted;
    }

    public ObservableList<PatientMedicines> getPatientMedicinesList() throws SQLException {
        patientMedicinesList = FXCollections.observableArrayList();
        patientMedicinesList = PatientMedicinesDB.getPatientMedicinesList(this.getId());
        return patientMedicinesList;
    }

    // Setter
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setIsIsolted(long isIsolted) {
        this.isIsolted = isIsolted;
    }
    public void setBedId(long bedId) {
        this.bedId = bedId;
    }
    public void setAdmitted(String admitted) {
        this.admitted = admitted;
    }



}
