package sample.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import sample.Models.BasicModel.Doctor;
import sample.Models.BasicModel.Employee;
import sample.Models.BasicModel.Nurse;
import sample.Models.BasicModel.Patient;
import sample.Models.PatientDB;


public class ViewPatientController implements Initializable {

    public TableView <Patient>tvPatient;
    public TableColumn<Patient,Long> colPatientID;
    public TableColumn <Patient,String >colName;
    public TableColumn <Patient,String>colPhone;
    public TableColumn <Patient,String>colGender;
    public TableColumn <Patient,String>colAdmittedAt;
    public TableColumn<Patient, Long> colIsolate;
    public TableColumn <Patient,Long>colBedID;

    public TextField txtPatientID;
    public TextField txtName;
    public TextField txtPhone;
    public TextField txtGender;
    public TextField txtBedID;
    public CheckBox chkNeedIsolate;
    public Label lblAdmittedAt;
    public Button btnSearch;

    Employee employee=null;
    Doctor doctor;
    Nurse nurse;
    Patient patient;

    /*
     * the staff properties will assign to the staff her to record what he does
     * because the ChangePassword.fxml
     * */

    public void initValue(Employee employee){
        this.employee=employee;
    }

    /*
     * this function call another function called GetPatientsList() from PatientDB class in folder -> Models
     * the getPatientsList() return a list of Patients that active in system
     * and put it all in table view
     * */

    public void showPatient() throws SQLException {
        ObservableList<Patient> patientsList = PatientDB.GetPatientsList();
        colPatientID.setCellValueFactory(new PropertyValueFactory<Patient,Long>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Patient,String>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<Patient,String>("phone"));
        colGender.setCellValueFactory(new PropertyValueFactory<Patient,String>("gender"));
        colIsolate.setCellValueFactory(new PropertyValueFactory<Patient, Long>("isIsolted"));
        colAdmittedAt.setCellValueFactory(new PropertyValueFactory<Patient, String>("admitted"));
        colBedID.setCellValueFactory(new PropertyValueFactory<Patient, Long>("bedId"));
        tvPatient.setItems(patientsList);
    }

    /*
     * here when the page render the first thing call is this function
     * and then this function call another function like  showPatient()
     * */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try { showPatient(); } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        btnSearch.setOnAction(actionEvent -> {
            try { searchPatient(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }

    /*
     * this function using when he selected row from table view
     * and then show this information in the text field and label
     * */

    public void getSelectedRow() {
        Patient patient=tvPatient.getSelectionModel().getSelectedItem();
        txtPatientID.setText(String.valueOf(patient.getId()));
        txtName.setText(patient.getName());
        txtPhone.setText(patient.getPhone());
        txtGender.setText(patient.getGender());
        txtBedID.setText(String.valueOf(patient.getBedId()));
        chkNeedIsolate.setSelected(patient.getIsIsolted() == 1);
        lblAdmittedAt.setText(String.valueOf(patient.getAdmitted()));
    }

    /*
     * this function attach with the search button
     * when he pressed it then check first it the text field is empty or not. if it empty then show alert
     * else, call function CheckPatient() from Doctor or Nurse class in folder -> Models
     * that search for this patient id and return the info in the object patients
     * if that id doesn't exist then show alert with that
     * if he exist then fill the text field and label with information
     * */

    public void searchPatient() throws SQLException {
        if (txtPatientID.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Patient ID First");
            alert.showAndWait();
        }
        else {
            if (employee.getType().equals("Doctor")){
                doctor=new Doctor(employee);
                patient = doctor.CheckPatient(Long.parseLong(txtPatientID.getText()));
            }
            else if(employee.getType().equals("Nurse")){
                nurse=new Nurse(employee);
                patient = nurse.CheckPatient(Long.parseLong(txtPatientID.getText()));
            }

            if (patient.getId()==0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("There Is No Patient With This ID");
                alert.showAndWait();
            }
            else {
                txtPatientID.setText(String.valueOf(patient.getId()));
                txtName.setText(patient.getName());
                txtPhone.setText(patient.getPhone());
                txtGender.setText(patient.getGender());
                txtBedID.setText(String.valueOf(patient.getBedId()));
                chkNeedIsolate.setSelected(patient.getIsIsolted() == 1);
                lblAdmittedAt.setText(String.valueOf(patient.getAdmitted()));
            }

        }

    }

}
