package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Models.*;
import sample.Models.BasicModel.Doctor;
import sample.Models.BasicModel.Employee;
import sample.Models.BasicModel.Patient;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DoctorPrescriptionController implements Initializable {

    public TableView<PatientMedicines>tvPrescription;
    public TableColumn <PatientMedicines, Long>colPrescriptionID;
    public TableColumn<PatientMedicines, Long> colPatientID;
    public TableColumn<PatientMedicines, Long> colDoctorID;
    public TableColumn <PatientMedicines, Long>colMedicineID;
    public TableColumn <PatientMedicines, String>colReason;
    public TableColumn <PatientMedicines, Long>colEvery;
    public TableColumn <PatientMedicines, Long>colAmount;

    public ComboBox cmbMedicine;
    public TextField txtPatientID;
    public TextField txtAmount;
    public TextField txtEvery;
    public TextArea txtReason;
    public Button btnAdd;
    public Button btnEdit;
    public Button btnDelete;
    public Button btnSearch;

    boolean selectedMedicines=false;
    boolean flag=false;

    ObservableList<Medicines>  medicines= FXCollections.observableArrayList();

    PatientMedicines patientMedicines=new PatientMedicines();
    PatientMedicines updatePatientMedicines=new PatientMedicines();

    Employee employee = null ;
    Doctor doctor;
    Patient patient;


    /*
     * the Doctor properties will assign to the staff her to record what he does
     * */

    public void initValue(Employee employee){
        this.employee=employee;
        doctor=new Doctor(employee);
    }

    /*
    * here when the page render the first thing call is this function
    * and then this function call another function like getMedicinesList() from MedicinesDB class in folder -> Models
    * that return all information of medicine in database in list of medicines object
    * and in it anther function call like  getMedicinesNameList() from MedicinesDB class in folder -> Models
    * that return the all MedicinesName in database in list
    * then put it in combo box -> cmbMedicine
    * */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        doctor=new Doctor();
        try {
            medicines = MedicinesDB.getMedicinesList();
            cmbMedicine.setItems(FXCollections.observableArrayList(MedicinesDB.getMedicinesNameList()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        btnSearch.setOnAction(actionEvent -> {
            try { searchPatient(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        btnAdd.setOnAction(actionEvent -> {
            try { addPrescription(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        btnEdit.setOnAction(actionEvent -> {
            try { editPrescription(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        btnDelete.setOnAction(actionEvent -> {
            try { deletePrescription(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    /*
    * this function take the patient id and search for his prescription and still active
    * using the function getPatientMedicinesList() from PatientMedicinesDB class in folder -> Models
    * */

    public void showPatientMedicines(long patientID) throws SQLException {
        ObservableList<PatientMedicines> patientMedicinesList=PatientMedicinesDB.getPatientMedicinesList(patientID);

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
    * else, call function getPatient() from PatientDB class in folder -> Models
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
            patient = doctor.CheckPatient(Long.parseLong(txtPatientID.getText()));
            if (patient.getId() == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("There Is No Patient With This ID");
                alert.showAndWait();
            } else {
                flag = true;
                txtPatientID.setText(String.valueOf(patient.getId()));
                showPatientMedicines(patient.getId());
            }
        }

    }

    /*
    * this function using when he selected row from table view
    * and then show this information in the text field
    * */

    public void getSelectedRow() {
        PatientMedicines patientMedicines=tvPrescription.getSelectionModel().getSelectedItem();
        int val = (int) patientMedicines.getMedicineId();
        val--;
        cmbMedicine.setValue(medicines.get(val).getName());
        txtAmount.setText(String.valueOf(patientMedicines.getDoseAmount()));
        txtEvery.setText(String.valueOf(patientMedicines.getHourTime()));
        txtReason.setText(patientMedicines.getReason());

        updatePatientMedicines.setPatientId(patientMedicines.getPatientId());
        updatePatientMedicines.setPrescriptionId(patientMedicines.getPrescriptionId());
        updatePatientMedicines.setMedicineId(patientMedicines.getMedicineId());
        updatePatientMedicines.setDoseAmount(patientMedicines.getDoseAmount());
        updatePatientMedicines.setHourTime(patientMedicines.getHourTime());
        updatePatientMedicines.setReason(patientMedicines.getReason());

    }

    /*
    * when he select the medicine from the combo box -> cmbMedicine
    * then take the id of that choice to insert it in database
    * */

    public void getMedicineID() {
        selectedMedicines=true;
        patientMedicines.setMedicineId(medicines.get(cmbMedicine.getSelectionModel().getSelectedIndex()).getMedicineId());
    }

    /*
    * first check if all text field is not empty
    * if it is ,then show alert
    * if all is ok then call function AddPrescription() from Doctor class in folder -> Models
    * and then return to call showPatientMedicines() to make refresh to the table view
    * */

    public void addPrescription() throws SQLException {
       if ( !flag ||txtAmount.getText().isEmpty() || txtEvery.getText().isEmpty() || txtReason.getText().isEmpty() || txtPatientID.getText().isEmpty() || !selectedMedicines){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setContentText("Please Fill All DATA");
           alert.showAndWait();
       }
       else {
           patientMedicines.setPatientId(patient.getId());
           patientMedicines.setDoctorId(employee.getId());
           patientMedicines.setDoseAmount(Long.parseLong(txtAmount.getText()));
           patientMedicines.setHourTime(Long.parseLong(txtEvery.getText()));
           patientMedicines.setReason(txtReason.getText());

           doctor.AddPrescription(patientMedicines);

           showPatientMedicines(patientMedicines.getPatientId());
           clear();
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setHeaderText(null);
           alert.setContentText("Data has been Added");
           alert.showAndWait();
       }

    }

    /*
    * the Doctor can edit the reason and the Amount and the hour time of the prescription only
    * then if he select on row from table view then
    * he can change the reason and the Amount and the hour time
    * first check if there is any of this fields is empty if it is then show alert
    * else, we check if the data are the same if it is then show alert say "there is no updated data"
    * else, make the update using the function EditPrescription() in Doctor class in folder -> Models
    * */

    public void editPrescription() throws SQLException {
        patientMedicines.setDoseAmount(Long.parseLong(txtAmount.getText()));
        patientMedicines.setHourTime(Long.parseLong(txtEvery.getText()));
        patientMedicines.setReason(txtReason.getText());
        if (txtAmount.getText().isEmpty()||txtEvery.getText().isEmpty() ||txtReason.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else if (updatePatientMedicines.equals(patientMedicines)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("There is no updated data");
            alert.showAndWait();
        }
        else {
            patientMedicines.setPrescriptionId(updatePatientMedicines.getPrescriptionId());

            doctor.EditPrescription(patientMedicines);

            showPatientMedicines(patientMedicines.getPatientId());
            clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data has been updated");
            alert.showAndWait();

        }

    }

    /*
    * the Doctor when he click on deleted button
    * then we don't delete the row we put the active = 0 and don't show this prescription information
    * that has done by using the DeletePrescription() in Doctor class in folder -> Models
    * */

    public void deletePrescription() throws SQLException {
        if (txtAmount.getText().isEmpty() || txtEvery.getText().isEmpty() || txtReason.getText().isEmpty() || txtPatientID.getText().isEmpty() || !selectedMedicines) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else {
            patientMedicines.setPrescriptionId(updatePatientMedicines.getPrescriptionId());

            doctor.DeletePrescription(patientMedicines);

            showPatientMedicines(updatePatientMedicines.getPatientId());
            clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data has been Deactivated");
            alert.showAndWait();
        }

    }

    /*
     * this function used to clear the text field
     * */

    private void clear(){
        txtAmount.clear();
        txtReason.clear();
        txtEvery.clear();
    }

}