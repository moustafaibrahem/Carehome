package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Models.*;
import sample.Models.BasicModel.Employee;
import sample.Models.BasicModel.Nurse;
import sample.Models.BasicModel.Patient;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NurseMedicinesAdministrationController implements Initializable {

    public TextField txtPatientID;
    public TableView <PatientMedicines>tvPrescription;
    public TableColumn <PatientMedicines, Long>colPrescriptionID;
    public TableColumn <PatientMedicines, Long>colPatientID;
    public TableColumn <PatientMedicines, Long>colDoctorID;
    public TableColumn <PatientMedicines, Long>colMedicineID;
    public TableColumn <PatientMedicines, String>colReason;
    public TableColumn <PatientMedicines, Long>colEvery;
    public TableColumn <PatientMedicines, Long>colAmount;
    public Label lblMedicine;
    public Label lblRemaining;
    public Label lblAmount;
    public Button btnSearch;
    public Button btnGivePrescription;

    boolean flag=false;
    long remaining;
    ObservableList<Medicines> medicines = FXCollections.observableArrayList();

    Administrations administrations = new Administrations();
    PatientMedicines patientMedicines=new PatientMedicines();

    Employee employee = null ;
    Nurse nurse;
    Patient patient;

    /*
     * the nurse properties will assign to the staff her to record what he does
     * */

    public void initValue(Employee employee){
        this.employee=employee;
        nurse=new Nurse(employee);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSearch.setOnAction(actionEvent -> {
            try { searchPatient(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        btnGivePrescription.setOnAction(actionEvent -> {
            try { givePrescription(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    /*
     * this function take the patient id and search for his prescription and still active
     * using the function getPatientMedicinesList() from PatientMedicinesDB class in folder -> Models
     * */

    public void showPatientMedicines(long patientID) throws SQLException {
        ObservableList<PatientMedicines> patientMedicinesList = PatientMedicinesDB.getPatientMedicinesList(patientID);

        colPrescriptionID.setCellValueFactory(new PropertyValueFactory<PatientMedicines,Long>("prescriptionId"));
        colPatientID.setCellValueFactory(new PropertyValueFactory<PatientMedicines,Long>("patientId"));
        colDoctorID.setCellValueFactory(new PropertyValueFactory<PatientMedicines,Long>("doctorId"));
        colMedicineID.setCellValueFactory(new PropertyValueFactory<PatientMedicines,Long>("medicineId"));
        colReason.setCellValueFactory(new PropertyValueFactory<PatientMedicines,String>("reason"));
        colEvery.setCellValueFactory(new PropertyValueFactory<PatientMedicines,Long>("hourTime"));
        colAmount.setCellValueFactory(new PropertyValueFactory<PatientMedicines, Long>("doseAmount"));

        tvPrescription.setItems(patientMedicinesList);
    }

    /*
     * this function attach with the search button
     * when he pressed it then check first it the text field is empty or not. if it empty then show alert
     * else, call function CheckPatient() from Nurse class in folder -> Models
     * that search for this patient id and return the info in the object patients
     * if that id doesn't exist then show alert with that
     * if he exist then call the function  showPatientMedicines() that in the top
     * */

    public void searchPatient() throws SQLException {
        flag=false;
        if (txtPatientID.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Patient ID First");
            alert.showAndWait();
        }
        else {
            patient = nurse.CheckPatient(Long.parseLong(txtPatientID.getText()));
            if (patient.getId()==0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("There Is No Patient With This ID");
                alert.showAndWait();
            }
            else {
                flag = true;
                txtPatientID.setText(String.valueOf(patient.getId()));
                showPatientMedicines(patient.getId());
            }
        }
    }

    /*
     * this function using when he selected row from table view
     * and then show this information in the label and combo box
     * and select the remaining time by compare the last taken date with sysdate and the hour prescription
     *
     * */

    public void getSelectedRow() throws SQLException {
        PatientMedicines patientMedicines=tvPrescription.getSelectionModel().getSelectedItem();

        medicines=MedicinesDB.getMedicinesList();
        if (patientMedicines!=null) {
            int val = (int) patientMedicines.getMedicineId();
            val--;
            lblMedicine.setText(medicines.get(val).getName());
            lblAmount.setText(String.valueOf(patientMedicines.getDoseAmount()));
            System.out.println(patientMedicines.getPrescriptionId());
            this.patientMedicines.setPatientId(patientMedicines.getPatientId());
            this.patientMedicines.setPrescriptionId(patientMedicines.getPrescriptionId());
            this.patientMedicines.setMedicineId(patientMedicines.getMedicineId());
            this.patientMedicines.setDoseAmount(patientMedicines.getDoseAmount());
            this.patientMedicines.setHourTime(patientMedicines.getHourTime());
            this.patientMedicines.setReason(patientMedicines.getReason());

            if (AdministrationsDB.getRowNumber(patientMedicines.getPrescriptionId())==0){
                AdministrationsDB.addAdministrations(patientMedicines.getPrescriptionId(),employee.getId());
            }
            administrations=AdministrationsDB.getAdministrations(patientMedicines.getPrescriptionId());
            remaining= patientMedicines.getHourTime()-administrations.getDef();
            if (remaining>0){ lblRemaining.setText(String.valueOf(remaining)); }
            else{ lblRemaining.setText("0"); }


        }

    }

    /*
    * first check if he click on search and put the right patient id
    * if he doesn't show alter "Please Enter Patient ID First "
    * else, then check if the remaining time less than 0 then he want to take the prescription
    * else then show alter that "not yet"
    * */

    public void givePrescription() throws SQLException {
        if (!flag){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Patient ID First ");
            alert.showAndWait();
        }
        else if(remaining > 0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Not Yet");
            alert.showAndWait();
        }
        else {

            nurse.GivePrescription(patientMedicines);

            showPatientMedicines(patient.getId());
            clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("The prescription Was Taken Successfully");
            alert.showAndWait();

        }
    }

    /*
     * this function used to clear the label
     * */

    public void clear(){
        lblRemaining.setText(" ");
        lblMedicine.setText("Medicine");
        lblAmount.setText("Amount");
    }



}
