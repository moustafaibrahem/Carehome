package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.Models.*;
import sample.Models.BasicModel.Employee;
import sample.Models.BasicModel.Nurse;
import sample.Models.BasicModel.Patient;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NurseChangePatientBedController implements Initializable {

    public TextField txtPatientID;
    public Label lblBedID;
    public ComboBox cmbRoom;
    public ComboBox cmbAvailableBeds;
    public ComboBox cmbWard;
    public Button btnSearch;
    public Button btnConfirm;

    boolean wardSelected=false;
    boolean roomSelected=false;
    boolean bedSelected=false;
    boolean flag=false;


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

    /*
     * here when the page render the first thing call is this function
     * and then this function call another function like  getWardsIdList() that return the all ward in database in list
     * then put it in combo box -> cmbWard
     * */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cmbWard.setItems(FXCollections.observableArrayList(WardsDB.getWardsIdList()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        btnSearch.setOnAction(actionEvent -> {
            try { searchPatient(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        btnConfirm.setOnAction(actionEvent -> {
            try { ConfirmChangeBed(); } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    /*
     * this function attach with the search button
     * when he pressed it then check first it the text field is empty or not. if it empty then show alert
     * else, call function CheckPatient() from Nurse class in folder -> Models
     * that search for this patient id and return the info in the object patients
     * if that id doesn't exist then show alert with that
     * if he exist then put in the lblBedID his BedId
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
                flag=false;
            }
            else{
                flag=true;
                lblBedID.setText(String.valueOf(patient.getBedId()));
            }

        }
    }

    /*
     * then when he select the ward id
     * Depending on the properties of patient  it will pass to function to get the correct rooms to choice
     * this function called getRoomsIdList() in RoomsDB class in folder -> Models
     * to return the list of rooms under this choice
     * then put it in combo box -> cmbRoom
     * */

    public void selectedWard() throws SQLException {
        if (flag) {
            wardSelected=true;
            cmbRoom.getItems().clear();
            boolean choise=false;
            if (patient.getIsIsolted()==1) choise=true;
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
        if (flag && wardSelected) {
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
        if (wardSelected && flag && roomSelected){
            bedSelected=true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }

    }

    /*
     * first check if all text field is not empty
     * if it is ,then show alert
     * if all is ok then call function ChangeBedFromTo in nurse class in folder ->Models
     * then call function updatePatientBed() in PatientDB class in folder -> Models
     * to assign a new bed to this patient
     * then call function changeBedFromTo() in ChangesBedsDB class in folder -> Models
     * to insert a row of this change that this patient move from bed of id to bed of id
     * then call function getRoom() in RoomsDB class in folder -> Models
     * to get the type of the new room to change if it was "free" to the new type that's depend on patient gender
     * so call function updateRoomType() in RoomsDB class in folder -> Models
     * then get the previous room id to check if there is patient in this room or empty
     * if it empty then we need to change the type to "free"
     * using the function getRoomId() in RoomsDB class in folder -> Models
     * and using the function updateRoomType() in RoomsDB class in folder -> Models
     * */

    public void ConfirmChangeBed() throws SQLException {
        if (!flag || !wardSelected || !roomSelected || !bedSelected ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();
        }
        else{
            String selectedRoom= String.valueOf(cmbRoom.getValue());
            String selectedBed = String.valueOf(cmbAvailableBeds.getValue());
            nurse.ChangeBedFromTo(patient,selectedBed,selectedRoom);

            clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Data has been updated");
            alert.showAndWait();
        }
    }

    /*
     * this function used to clear the text field and combo box
     * */

    void clear() throws SQLException {
        txtPatientID.clear();
        lblBedID.setText(" ");
        cmbRoom.getItems().clear();
        cmbAvailableBeds.getItems().clear();
        cmbWard.getItems().clear();
        cmbWard.setItems(FXCollections.observableArrayList(WardsDB.getWardsIdList()));
        wardSelected=false;
        roomSelected=false;
        bedSelected=false;
        flag=false;
    }


}
