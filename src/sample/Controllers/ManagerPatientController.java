package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Models.*;
import sample.Models.BasicModel.Employee;
import sample.Models.BasicModel.Manager;
import sample.Models.BasicModel.Patient;
import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class ManagerPatientController implements Initializable {

    public TextField txtName;
    public TextField txtPhone;

    public ComboBox cmbGender;
    public ComboBox cmbAvailableBeds;
    public ComboBox cmbRoom;
    public ComboBox cmbWard;

    public DatePicker dpAdmittedAt;
    public CheckBox chkNeedIsolate;

    public TableView <Patient>tvPatient;
    public TableColumn<Patient,Long> colPatientID;
    public TableColumn <Patient,String >colName;
    public TableColumn <Patient,String>colPhone;
    public TableColumn <Patient,String>colGender;
    public TableColumn <Patient,String>colAdmittedAt;
    public TableColumn<Patient, Long> colIsolate;
    public TableColumn <Patient,Long>colBedID;

    final ObservableList genderOption= FXCollections.observableArrayList();
    public Button btnDelete;
    public Button btnUpdate;
    public Button btnAddPatient;

    boolean dpSelected=false;
    boolean genderSelected=false;
    boolean isolateCheckBox=true;
    boolean wardSelected=false;
    boolean roomSelected=false;
    boolean bedSelected=false;
    boolean choise=false;

    Manager manager=null;
    Employee doAction=null;

    Patient patient;
    Patient patientUpdate;

    /*
     * the manager properties will assign to the staff her to record what he does
     * */

    public void initValue(Employee employee){
        this.doAction=employee;
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
    * and getWardsIdList() that return the all ward in database in list
    * then put it in combo box -> cmbWard
    *
    * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manager=new Manager();
        patient=new Patient();
        patientUpdate=new Patient();

        genderOption.add("male");
        genderOption.add("female");
        cmbGender.setItems(FXCollections.observableArrayList(genderOption));

        try {
            showPatient();
            cmbWard.setItems(FXCollections.observableArrayList(WardsDB.getWardsIdList()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        btnAddPatient.setOnAction(actionEvent -> {
            try { addPatient(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        btnUpdate.setOnAction(actionEvent -> {
            try { updatePatient(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        btnDelete.setOnAction(actionEvent -> {
            try { deletePatient(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


    }

    /*
    * when he choose the gender put it in patient gender
    * */

    public void selectedGender() {
        genderSelected=true;
        patient.setGender(String.valueOf(cmbGender.getValue()));
    }

    /*
     * when he check he is isolated then make it in his attribute
     * */

    public void getSelected() {
        isolateCheckBox=true;
        if (chkNeedIsolate.isSelected()){
            patient.setIsIsolted(1);
            choise=true;
        }
        else {
            patient.setIsIsolted(0);
            choise=false;
        }
    }

    /*
    * then when he select the ward id
    * Depending on the previous selections all of it will pass to function to get the correct rooms to choice
    * this function called getRoomsIdList() in RoomsDB class in folder -> Models
    * to return the list of rooms under this choice
    * then put it in combo box -> cmbRoom
    * */

    public void selectedWard() throws SQLException {
        if (genderSelected&&isolateCheckBox) {
            wardSelected=true;
            cmbRoom.getItems().clear();
            cmbRoom.setItems(FXCollections.observableArrayList(RoomsDB.getRoomsIdList(String.valueOf(cmbWard.getValue()), patient.getGender(), choise)));
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
    }

    /*
    * when he choice the room id then in
    * then call another function to get the list of beds free in this room called getBedsIdList() in BedsDB class in folder -> Models
    * then put it in combo box -> cmbAvailableBeds
    * */

    public void selectedRoom() throws SQLException {
        if (genderSelected && isolateCheckBox && wardSelected) {
            cmbAvailableBeds.getItems().clear();
            roomSelected=true;
            cmbAvailableBeds.setItems(BedsDB.getBedsIdList(String.valueOf(cmbRoom.getValue())));
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
    }

    /*
    * if he select all now he select bed
    * */

    public void selectedBed() {
        if (genderSelected && isolateCheckBox && wardSelected && roomSelected){
            bedSelected=true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }

    }


    public void getDP() {
        dpSelected=true;
    }

    /*
    * first check if all text field is not empty
    * if it is ,then show alert
    * if all is ok then call function AddPatient() in Manager class in folder -> Models
    * then update the room type if he is free or the same type of gender of patient
    * */

    public void addPatient() throws SQLException {
        patient.setName(txtName.getText());
        patient.setPhone(txtPhone.getText());
        String admittedDate = dpAdmittedAt.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (patient.getName().isEmpty()||patient.getPhone().isEmpty() || !bedSelected || !roomSelected || !wardSelected || !isolateCheckBox || !genderSelected || !dpSelected){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else {
            String roomId= String.valueOf(cmbRoom.getValue());
            String bedId=String.valueOf(cmbAvailableBeds.getValue());
            patient.setAdmitted(admittedDate);
            patient.setBedId(Long.parseLong(bedId));
            manager.AddPatient(patient);
            RoomsDB.updateRoomType(patient.getGender(),roomId);
            showPatient();
            clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data has been Added");
            alert.showAndWait();
        }

    }

    /*
    * the manager can edit the name and the phone of the patient only
    * then if he select on row from table view then
    * he can change name or phone
    * first check if there is any of this fields is empty if it is then show alert
    * else, we check if the data are the same if it is then show alert say "there is no updated data"
    * else, make the update using the function EditPatient() in Manager class in folder -> Models
     * */

    public void updatePatient() throws SQLException {
        patient.setId(patientUpdate.getId());
        patient.setName(txtName.getText());
        patient.setPhone(txtPhone.getText());
        if (patient.getName().isEmpty()||patient.getPhone().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else if (patientUpdate.equals(patient)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("There is no updated data");
            alert.showAndWait();
        }
        else{
            manager.EditPatient(patient);
            showPatient();
            clear();
        }
    }

    /*
    * the manager when he click on deleted button
    * then we don't delete the row we put the dischargedDate with the sysdate and don't show his information
    * that has done by using the DeletePatient() in Manager class in folder -> Models
    * and then we check if he was the last one in this room or what if he was the last one
    * then we need to update the type of this room to "free"
    * and we did it using the function updateRoomType() in RoomsDB class in folder -> Models
    * */

    public void deletePatient() throws SQLException {
        patient.setId(patientUpdate.getId());
        patient.setName(txtName.getText());
        patient.setPhone(txtPhone.getText());
        if (patient.getName().isEmpty()||patient.getPhone().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else {
            patient.setBedId(patientUpdate.getBedId());
            manager.DeletePatient(patient);

            showPatient();
            clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data has been Deactivated");
            alert.showAndWait();
        }
    }

    /*
    * this function using when he selected row from table view
    * and then show this information in the text field
    * */

    public void getSelectedRow() {
        Patient patient=tvPatient.getSelectionModel().getSelectedItem();
        txtName.setText(patient.getName());
        txtPhone.setText(patient.getPhone());
        cmbGender.setValue(patient.getGender());

        patientUpdate.setId(patient.getId());
        patientUpdate.setName(patient.getName());
        patientUpdate.setPhone(patient.getPhone());
        patientUpdate.setBedId(patient.getBedId());
    }

    /*
    * this function used to clear the text field and combo box
    * */

    private void clear() throws SQLException {
        txtName.clear();
        txtPhone.clear();
        cmbRoom.getItems().clear();
        cmbAvailableBeds.getItems().clear();
        cmbWard.getItems().clear();
        cmbWard.setItems(FXCollections.observableArrayList(WardsDB.getWardsIdList()));
    }


}
